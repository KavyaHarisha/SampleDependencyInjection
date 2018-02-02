package com.sampledependencyinjection;

import android.app.Application;

import com.sampledependencyinjection.component.DaggerFirebaseComponent;
import com.sampledependencyinjection.component.FirebaseComponent;
import com.sampledependencyinjection.module.AppModule;
import com.sampledependencyinjection.module.FirebaseModule;

/**
 * Created by User on 2/1/2018.
 */

public class MyApp extends Application {
    private FirebaseComponent mFirebaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseComponent = DaggerFirebaseComponent.builder()
                .appModule(new AppModule(this))
                .firebaseModule(new FirebaseModule()).build();
    }

    public FirebaseComponent getmFirebaseComponent(){
        return mFirebaseComponent;
    }
}
