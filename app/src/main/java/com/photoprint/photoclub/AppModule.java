package com.photoprint.photoclub;

import com.photoprint.network.ApiConfig;

import dagger.Module;
import dagger.Provides;

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

    @Provides
    ApiConfig apiConfig() {
        return new ApiConfig(
                BuildConfig.API_BASE_URL
        );
    }
}
