package com.sampledependencyinjection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.sampledependencyinjection.data.User;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DatabaseReference mDatabaseReference;


    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDetails = (TextView) findViewById(R.id.txt_user);
        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        btnSave = (Button) findViewById(R.id.btn_save);
        ((MyApp)getApplication()).getmFirebaseComponent().inject(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, email);
                } else {
                    updateUser(name, email);
                }
            }
        });

        toggleButton();
    }

    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
        } else {
            btnSave.setText("Update");
        }
    }

    private void createUser(String name, String email) {
        if (TextUtils.isEmpty(userId)) {
            userId = mDatabaseReference.push().getKey();
        }

        User user = new User(name, email);

        mDatabaseReference.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        mDatabaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                txtDetails.setText(user.mName+","+user.mEmail);
                inputEmail.setText("");
                inputName.setText("");
                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"connection lossed",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUser(String name, String email) {
        if (!TextUtils.isEmpty(name))
            mDatabaseReference.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mDatabaseReference.child(userId).child("email").setValue(email);
    }
}
