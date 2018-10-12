package com.photoprint.photoclub.data.authtokenstorage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.photoprint.network.auth.AuthTokenStorage;
import com.photoprint.photoclub.data.apppreferences.AppPrefs;

import javax.inject.Inject;

/**
 * Хранилище для токена авторизации
 *
 * @author Grigoriy Pryamov
 */
public class AuthTokenStorageImpl implements AuthTokenStorage {

    private final AppPrefs appPrefs;

    @Inject
    AuthTokenStorageImpl(AppPrefs appPrefs) {
        this.appPrefs = appPrefs;
    }

    @NonNull
    @Override
    public String load() {
        return appPrefs.getAuthToken();
    }

    @Override
    public void save(@Nullable String token) {
        appPrefs.setAuthToken(token);
    }

    @Override
    public boolean presenceOfToken() {
        return !load().equals(AppPrefs.Entities.DEFAULT_TOKEN);
    }

    @Override
    public void clear() {
        appPrefs.removeAuthToken();
    }
}
