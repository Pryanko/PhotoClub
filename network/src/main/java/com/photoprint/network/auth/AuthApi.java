package com.photoprint.network.auth;

import com.photoprint.network.auth.model.login.DataToken;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * @author Grigoriy Pryamov.
 */
public interface AuthApi {
    /**
     * Метод первичной регистрации пользователя.
     *
     * @return токен пользователья устройства.
     */
    @GET("register")
    Single<Response<DataToken>> register();
}
