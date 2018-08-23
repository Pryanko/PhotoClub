package com.photoprint.photoclub.tools;

import android.content.Context;

import com.photoprint.photoclub.BuildConfig;

import javax.inject.Inject;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

/**
 * Инструмент для стороннего полключения к БД приложения
 *
 * @author Grigoriy Pryamov
 */
public class SQLiteConnectionService {

    private final Context context;

    @Inject
    SQLiteConnectionService(Context context) {
        this.context = context;
    }

    public void start() {
        SQLiteStudioService.instance().setPort(BuildConfig.SQLITE_STUDIO_SERVICE_PORT);
        SQLiteStudioService.instance().start(context, com.photoprint.localdb.BuildConfig.DB_VERSION);
    }
}
