package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.photoprint.utils.Ref;
import com.photoprint.photoclub.model.User;
import com.photoprint.photoclub.repository.base.BaseRepository;

import io.reactivex.Single;

/**
 * Репозиторий пользователя
 *
 * @author Grigoriy Pryamov.
 */
public interface UserRepository extends BaseRepository<User, Long> {
    /**
     * Возвращает текущего пользователя.
     */
    @Nullable
    User getCurrent();

    /**
     * Возвращает текущего пользователя.
     */
    @NonNull
    Single<Ref<User>> getCurrentRx();

    /**
     * Удаляет все записи
     */
    void deleteAll();
}
