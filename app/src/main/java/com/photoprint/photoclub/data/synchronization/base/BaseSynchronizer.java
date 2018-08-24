package com.photoprint.photoclub.data.synchronization.base;

import android.support.annotation.NonNull;

import com.photoprint.network.Response;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Базовый класс синхронизации
 *
 * @author Grigoriy Pryamov.
 */
public abstract class BaseSynchronizer<entity> {

    protected final ApiWorker apiWorker;
    private final DbTransaction transaction;

    protected BaseSynchronizer(ApiWorker apiWorker,
                               DbTransaction transaction) {
        this.apiWorker = apiWorker;
        this.transaction = transaction;
    }

    /**
     * Основной метод синхронизации
     */
    public Completable sync() {
        return getApiSource()
                .map(Response::get)
                .map(Data::getData)
                .observeOn(AppSchedulers.db())
                .doOnSuccess(categories -> transaction.callInTx(() -> store(categories)))
                .toCompletable();
    }

    /**
     * Метод должен вернуть источник апи -
     */
    protected abstract Single<Response<Data<entity>>> getApiSource();

    /**
     * Метод записывающий данные в бд - реализаци вынесена в наследника, для реализации разной логики
     *
     * @param entities список для записи
     */
    protected abstract List<entity> store(@NonNull List<entity> entities);
}
