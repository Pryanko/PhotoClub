package com.photoprint.photoclub.auth;

import com.photoprint.network.Response;
import com.photoprint.network.auth.AuthApiWorker;
import com.photoprint.network.auth.model.login.DataToken;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Класс для регистрации на сервере и авторизации пользователя
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class AuthManager {

    private final AuthApiWorker authApiWorker;

    @Inject
    public AuthManager(AuthApiWorker authApiWorker) {
        this.authApiWorker = authApiWorker;
    }

    public Single<DataToken> register() {
        return authApiWorker
                .register()
                .map(Response::getData)
                .subscribeOn(AppSchedulers.network());
    }
}
