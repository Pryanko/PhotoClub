package com.photoprint.photoclub;

import android.content.Context;

import com.photoprint.network.ApiConfig;
import com.photoprint.photoclub.data.authtokenstorage.AuthTokenStorage;
import com.photoprint.photoclub.data.authtokenstorage.AuthTokenStorageImpl;

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
    Context context() {
        return app;
    }

    @Provides
    ApiConfig apiConfig() {
        return new ApiConfig(
                BuildConfig.API_BASE_URL
        );
    }

    @Provides
    AuthTokenStorage authTokenStorage(AuthTokenStorageImpl authTokenStorage) {
        return authTokenStorage;
    }
}
