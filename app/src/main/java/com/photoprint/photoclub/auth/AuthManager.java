package com.photoprint.photoclub.auth;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.auth.AuthApiWorker;
import com.photoprint.network.auth.model.login.DataToken;
import com.photoprint.network.auth.model.login.DeviceToken;
import com.photoprint.photoclub.data.authtokenstorage.AuthTokenStorage;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

/**
 * Класс для регистрации на сервере и авторизации пользователя
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class AuthManager {

    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

    private final AuthApiWorker authApiWorker;
    private final AuthTokenStorage authTokenStorage;

    @Inject
    AuthManager(AuthApiWorker authApiWorker,
                AuthTokenStorage authTokenStorage) {
        this.authApiWorker = authApiWorker;
        this.authTokenStorage = authTokenStorage;
    }

    public Completable register() {
        //При первом запуске, либо токен первичной регистрации был сброшен (не записан) -> получаем новый
        if (authTokenStorage.load() == null) {
            logger.trace("start register");
            return authApiWorker
                    .register()
                    .map(Response::getData)
                    .onErrorReturn(throwable -> new DataToken())
                    .flatMapCompletable(dataToken ->
                            Completable.fromAction(() -> {
                                        String token;
                                        DeviceToken deviceToken = dataToken.getDeviceToken();
                                        if (deviceToken != null) {
                                            token = deviceToken.getToken();
                                            logger.trace("successful register");
                                        } else {
                                            token = null;
                                            logger.trace("failed register");
                                        }
                                        authTokenStorage.save(token);
                                    }
                            ))
                    .subscribeOn(AppSchedulers.network());
        } else {
            logger.trace("primary registration is not required");
            return Completable.complete();
        }
    }
}
