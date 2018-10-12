package com.photoprint.photoclub.data.apppreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;

/**
 * Конфигурация приложения
 *
 * @author Grigoriy Pryamov
 */
@Singleton
public class AppPrefs {

    public static class Entities {
        /**
         * Ключ для prefs
         */
        private static final String AUTH_TOKEN = "AUTH_TOKEN";
        /**
         * Время синхронизации
         */
        private static final String DATA_SYNC_TIME = "DATA_SYNC_TIME";
        /**
         * Дефолтное значение токена
         */
        public static final String DEFAULT_TOKEN = "NOT_REGISTERED";
    }

    /**
     * Наименование файла
     */
    private static final String SHARED_PREFS_FILE_NAME = "AppPrefs";

    private final SharedPreferences prefs;

    @Inject
    AppPrefs(Context context) {
        prefs = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getAuthToken() {
        return prefs.getString(Entities.AUTH_TOKEN, Entities.DEFAULT_TOKEN);
    }

    public void setAuthToken(@Nullable String authToken) {
        prefs.edit().putString(Entities.AUTH_TOKEN, authToken).apply();
    }

    public void removeAuthToken() {
        prefs.edit().remove(Entities.AUTH_TOKEN).apply();
    }

    public long getDataSyncTime() {
        return prefs.getLong(Entities.DATA_SYNC_TIME, 0);
    }

    public void setDataSyncTime(long syncTime) {
        prefs.edit().putLong(Entities.DATA_SYNC_TIME, syncTime).apply();
    }
}
