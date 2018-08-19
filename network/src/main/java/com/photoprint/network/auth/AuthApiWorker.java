package com.photoprint.network.auth;

import com.photoprint.network.Response;
import com.photoprint.network.auth.model.login.DataToken;

import io.reactivex.Single;

/**
 * Api воркер для авторизации и регистрации пользователя
 *
 * @author Grigoriy Pryamov.
 */
public interface AuthApiWorker {

    Single<Response<DataToken>> register();
}
