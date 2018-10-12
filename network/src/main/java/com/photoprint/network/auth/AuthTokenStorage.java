package com.photoprint.network.auth;

import android.support.annotation.NonNull;
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
    @NonNull
    String load();

    /**
     * Сохраняет токен в хранилище
     *
     * @param token токен для сохранения
     */
    void save(@Nullable String token);

    /**
     * проверяет наличие токена
     */
    boolean presenceOfToken();

    /**
     * Очищает хранилище
     */
    void clear();
}
