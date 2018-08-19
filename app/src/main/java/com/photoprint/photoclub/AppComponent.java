package com.photoprint.photoclub;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(AppInitProvider appInitProvider);
}
