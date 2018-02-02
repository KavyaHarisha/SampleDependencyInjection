package com.sampledependencyinjection.module;

import com.sampledependencyinjection.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    MyApp mApp;

    public AppModule(MyApp application){
        mApp = application;
    }

    @Singleton
    @Provides
    MyApp providesApplication(){
        return mApp;
    }
}
