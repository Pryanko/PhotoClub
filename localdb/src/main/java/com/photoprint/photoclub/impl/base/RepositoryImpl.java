package com.photoprint.photoclub.impl.base;

import com.photoprint.photoclub.base.AppDatabase;

/**
 * Базовый репозиторий
 *
 * @author Grigoriy Pryamov.
 */
public abstract class RepositoryImpl {

    protected AppDatabase appDatabase;

    RepositoryImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }
}
