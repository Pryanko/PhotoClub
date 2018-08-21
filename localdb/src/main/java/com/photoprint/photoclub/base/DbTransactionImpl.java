package com.photoprint.photoclub.base;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

/**
 * Транзакции БД
 *
 * @author Grigoriy Pryamov.
 */
public class DbTransactionImpl implements DbTransaction {

    private final AppDatabase appDatabase;

    public DbTransactionImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void beginTransaction() {
        appDatabase.beginTransaction();
    }

    @Override
    public void endTransaction() {
        appDatabase.endTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        appDatabase.setTransactionSuccessful();
    }

    @Override
    public <T> T callInTx(@NonNull Callable<T> callable) {
        return appDatabase.runInTransaction(callable);
    }

    @Override
    public void callInTx(@NonNull Runnable runnable) {
        appDatabase.runInTransaction(runnable);
    }
}
