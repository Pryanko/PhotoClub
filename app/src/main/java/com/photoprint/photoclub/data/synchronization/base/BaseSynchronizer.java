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
 * @author Grigoriy Pryamov.
 */
public abstract class BaseSynchronizer<T> {

    protected final ApiWorker apiWorker;
    private final DbTransaction transaction;

    protected BaseSynchronizer(ApiWorker apiWorker,
                               DbTransaction transaction) {
        this.apiWorker = apiWorker;
        this.transaction = transaction;
    }

    public Completable sync() {
        return getSync()
                .map(Response::get)
                .map(Data::getData)
                .observeOn(AppSchedulers.db())
                .doOnSuccess(categories -> transaction.callInTx(() -> store(categories)))
                .toCompletable();
    }

    protected abstract Single<Response<Data<T>>> getSync();

    protected abstract List<T> store(@NonNull List<T> entities);
}
