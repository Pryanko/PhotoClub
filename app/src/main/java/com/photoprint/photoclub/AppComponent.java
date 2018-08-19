package com.photoprint.photoclub;

import com.photoprint.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(AppInitProvider appInitProvider);
}
