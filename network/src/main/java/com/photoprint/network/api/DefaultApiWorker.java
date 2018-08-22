package com.photoprint.network.api;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.ResponseConverter;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.category.Category;
import com.photoprint.network.api.model.manual.Guide;
import com.photoprint.network.api.model.services.Service;
import com.photoprint.network.auth.DefaultAuthApiWorker;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
public class DefaultApiWorker implements ApiWorker {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthApiWorker.class);

    private final Api api;

    public DefaultApiWorker(Api api) {
        this.api = api;
    }

    @Override
    public Single<Response<Data<Guide>>> getManuals() {
        return api.getManuals()
                .map(ResponseConverter::convert);
    }

    @Override
    public Single<Response<Data<Category>>> getCategory() {
        return api.getCategory()
                .map(ResponseConverter::convert);
    }

    @Override
    public Single<Response<Data<Service>>> getService() {
        return api.getService()
                .map(ResponseConverter::convert);
    }
}
