package com.photoprint.photoclub;

import android.content.Context;

import com.photoprint.network.ApiConfig;
import com.photoprint.photoclub.data.authtokenstorage.AuthTokenStorage;
import com.photoprint.photoclub.data.authtokenstorage.AuthTokenStorageImpl;
import com.photoprint.photoclub.data.storage.StorageManager;
import com.photoprint.photoclub.data.storage.StorageManagerImpl;
import com.photoprint.photoclub.helper.system.AppSystemMetrics;
import com.photoprint.photoclub.helper.system.AppSystemMetricsImpl;

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

    @Provides
    AppSystemMetrics appSystemMetrics(AppSystemMetricsImpl appSystemMetrics) {
        return appSystemMetrics;
    }

    @Provides
    StorageManager getStorageManager(StorageManagerImpl storageManager) {
        return storageManager;
    }

}
