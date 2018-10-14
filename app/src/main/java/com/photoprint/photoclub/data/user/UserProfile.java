package com.photoprint.photoclub.data.user;

import android.support.annotation.Nullable;

import com.photoprint.utils.Preconditions;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.model.User;
import com.photoprint.photoclub.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Постащик данных о пользователе
 *
 * @author Grigoriy Pryamov.
 */
public class UserProfile {

    private final UserRepository userRepository;
    private final DbTransaction dbTransaction;

    @Inject
    UserProfile(UserRepository userRepository,
                DbTransaction dbTransaction) {
        this.userRepository = userRepository;
        this.dbTransaction = dbTransaction;
    }

    /**
     * Метод возвращяющий текущего пользователя
     */
    @Nullable
    public User getCurrentUser() {
        return userRepository.getCurrent();
    }

    /**
     * Метод помечающий, что пользователь уже совершил первый запуск на устройстве
     */
    public Completable firstRunProduced() {
        return Completable
                .fromAction(() -> dbTransaction.callInTx(() -> {
                    User user = userRepository.getCurrent();
                    Preconditions.checkNotNull(user);
                    user.setFirstRun(true);
                    userRepository.update(user);
                }));
    }

    /**
     * Метод проверяющий был ли первый запуск
     */
    public Single<Boolean> isFirstRunApp() {
        return Single.fromCallable(() -> dbTransaction.callInTx(() -> {
            User user = userRepository.getCurrent();
            Preconditions.checkNotNull(user);
            //Внимание дефолтное значение false
            return !user.isFirstRun();
        }));
    }
}
