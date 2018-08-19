package com.photoprint.network.auth.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Интерцептор авторизации, добавляет в заголовк при авторизации при авторизации
 *
 * @author Grigoriy Pryamov.
 */
public class AuthInterceptror implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
