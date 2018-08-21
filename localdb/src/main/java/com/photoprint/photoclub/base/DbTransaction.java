package com.photoprint.photoclub.base;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

/**
 * Транзакция БД
 *
 * @author Grigoriy Pryamov.
 */
public interface DbTransaction {

    /**
     * Начинает транзакцию
     */
    void beginTransaction();

    /**
     * Завершает транзакцию
     */
    void endTransaction();

    /**
     * Помечает успешную транзакцию
     */
    void setTransactionSuccessful();

    /**
     * Выполняет блок кода в транзакции
     */
    <T> T callInTx(@NonNull Callable<T> callable);

    /**
     * Выполняет блок кода в транзакции
     */
    void callInTx(@NonNull Runnable runnable);

}
