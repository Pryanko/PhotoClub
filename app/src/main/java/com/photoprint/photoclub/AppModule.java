package com.photoprint.photoclub;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Di-модуль приложения
 *
 * @author Grigoriy Pryamov.
 */
@Module
class AppModule {

    private final App app;

    AppModule(App app) {
        this.app = app;
    }
}
