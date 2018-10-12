package com.photoprint.photoclub.auth;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.auth.AuthApiWorker;
import com.photoprint.network.auth.model.login.DataToken;
import com.photoprint.network.auth.model.login.DeviceToken;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.network.auth.AuthTokenStorage;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.User;
import com.photoprint.photoclub.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.annotations.NonNull;

/**
 * Класс для регистрации на сервере и авторизации пользователя
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class AuthManager {

    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

    private final AuthApiWorker authApiWorker;
    private final DbTransaction dbTransaction;
    private final UserRepository userRepository;
    private final AuthTokenStorage authTokenStorage;

    @Inject
    AuthManager(AuthApiWorker authApiWorker,
                DbTransaction dbTransaction,
                UserRepository userRepository,
                AuthTokenStorage authTokenStorage) {
        this.authApiWorker = authApiWorker;
        this.dbTransaction = dbTransaction;
        this.userRepository = userRepository;
        this.authTokenStorage = authTokenStorage;
    }

    public Completable register() {
        //При первом запуске, либо токен первичной регистрации был сброшен (не записан) -> получаем новый
        if (authTokenStorage.presenceOfToken()) {
            logger.trace("primary registration is not required");
            return Completable.complete();
        } else {
            logger.trace("start register");
            return authApiWorker
                    .register()
                    .map(Response::get)
                    .observeOn(AppSchedulers.db())
                    .doOnSuccess(dataToken -> dbTransaction.callInTx(() -> storeUserName(dataToken)))
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
        }
    }

    /**
     * Метод сохраняющий первичные данные пользователя
     */
    private DataToken storeUserName(@NonNull DataToken dataToken) {
        //Удаляем предыдущую запись
        userRepository.deleteAll();
        User user = new User();
        user.setUsername(dataToken.getDeviceToken().getUsername());
        userRepository.insert(user);
        return dataToken;
    }
}
