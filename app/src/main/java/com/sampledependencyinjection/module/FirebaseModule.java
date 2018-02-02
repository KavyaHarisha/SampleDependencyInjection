package com.sampledependencyinjection.module;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class FirebaseModule {

    @Singleton
    @Provides
    DatabaseReference provideFireBase(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        return databaseReference;
    }

}
