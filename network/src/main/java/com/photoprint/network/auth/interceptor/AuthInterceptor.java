package com.photoprint.network.auth.interceptor;

import android.support.annotation.NonNull;

import com.photoprint.network.auth.AuthTokenStorage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Интерцептор авторизации, добавляет токен в заголовк при запросах
 *
 * @author Grigoriy Pryamov.
 */
public class AuthInterceptor implements Interceptor {

    private static final String HEADER_ACCESS_TOKEN = "x-access-token";

    private final AuthTokenStorage authTokenStorage;

    public AuthInterceptor(AuthTokenStorage authTokenStorage) {
        this.authTokenStorage = authTokenStorage;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = authTokenStorage.load();
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader(HEADER_ACCESS_TOKEN, token);
        return chain.proceed(requestBuilder.build());
    }
}
