package com.photoprint.network.auth.interceptor;

import com.photoprint.network.auth.AuthTokenStorage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Интерцептор авторизации, добавляет в заголовк при запросах
 *
 * @author Grigoriy Pryamov.
 */
public class AuthInterceptror implements Interceptor {

    private static final String HEADER_ACCESS_TOKEN = "x-access_token";

    private final AuthTokenStorage authTokenStorage;

    public AuthInterceptror(AuthTokenStorage authTokenStorage) {
        this.authTokenStorage = authTokenStorage;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader(HEADER_ACCESS_TOKEN, authTokenStorage.load());
        return chain.proceed(requestBuilder.build());
    }
}
