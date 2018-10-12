package com.photoprint.network.auth;

import com.photoprint.network.auth.interceptor.AuthInterceptror;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;

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
    public HttpClientBuilder(OkHttpClient baseOkHttpClient,
                             AuthTokenStorage authTokenStorage) {
        this.baseOkHttpClient = baseOkHttpClient;
        this.authTokenStorage = authTokenStorage;
    }

    @NonNull
    public OkHttpClient build() {
        return baseOkHttpClient.newBuilder()
                .addInterceptor(new AuthInterceptror(authTokenStorage))
//                .addInterceptor(new AppInfoOkHttpInterceptor(applicationInfo))
//                .addInterceptor(new DeviceInfoOkHttpInterceptor(deviceInfo))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
