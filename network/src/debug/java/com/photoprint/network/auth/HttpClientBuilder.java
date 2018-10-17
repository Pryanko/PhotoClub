package com.photoprint.network.auth;

import com.photoprint.network.auth.interceptor.AuthInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Grigoriy Pryamov.
 */
public class HttpClientBuilder {

    /**
     * Таймаут на подключение/чтение/запись (в секундах)
     */
    private static final long TIMEOUT = 15;
    /**
     * Http-клиент.
     */
    private final OkHttpClient baseOkHttpClient;

    private final AuthTokenStorage authTokenStorage;

    @Inject
    HttpClientBuilder(OkHttpClient baseOkHttpClient,
                             AuthTokenStorage authTokenStorage) {
        this.baseOkHttpClient = baseOkHttpClient;
        this.authTokenStorage = authTokenStorage;
    }

    @NonNull
    public OkHttpClient build() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return baseOkHttpClient.newBuilder()
                .addInterceptor(new AuthInterceptor(authTokenStorage))
//                .addInterceptor(new AppInfoOkHttpInterceptor(applicationInfo))
//                .addInterceptor(new DeviceInfoOkHttpInterceptor(deviceInfo))
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
