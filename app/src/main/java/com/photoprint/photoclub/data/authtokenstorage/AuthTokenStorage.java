package com.photoprint.photoclub.data.authtokenstorage;

import android.support.annotation.Nullable;

/**
 * Хранилище для токена авторизации
 *
 * @author Grigoriy Pryamov
 */
public interface AuthTokenStorage {
    /**
     * Загружает сохранённый в хранилище токен
     *
     * @return сохранённый в хранилище токен
     */
    @Nullable
    String load();

    /**
     * Сохраняет токен в хранилище
     *
     * @param token токен для сохранения
     */
    void save(@Nullable String token);

    /**
     * Очищает хранилище
     */
    void clear();
}
