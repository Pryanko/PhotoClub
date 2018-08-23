package com.photoprint.network.auth;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;

/**
 * Билдер http-клиента для авторизации.
 *
 * @author Grigoriy Pryamov.
 */
public class AuthHttpClientBuilder {
    /**
     * Таймаут на подключение/чтение/запись (в секундах)
     */
    private static final long TIMEOUT = 15;
    /**
     * Http-клиент.
     */
    private final OkHttpClient baseOkHttpClient;

    @Inject
    public AuthHttpClientBuilder(OkHttpClient baseOkHttpClient) {
        this.baseOkHttpClient = baseOkHttpClient;
    }

    @NonNull
    public OkHttpClient build() {
        return baseOkHttpClient.newBuilder()
//                .addInterceptor(new AppInfoOkHttpInterceptor(applicationInfo))
//                .addInterceptor(new DeviceInfoOkHttpInterceptor(deviceInfo))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
