package com.sampledependencyinjection.component;

import com.sampledependencyinjection.MainActivity;
import com.sampledependencyinjection.module.AppModule;
import com.sampledependencyinjection.module.FirebaseModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class,FirebaseModule.class})
public interface FirebaseComponent {
    void inject(MainActivity activity);
}
