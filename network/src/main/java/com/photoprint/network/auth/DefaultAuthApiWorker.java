package com.photoprint.network.auth;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.ResponseConverter;
import com.photoprint.network.auth.model.login.DataToken;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
public class DefaultAuthApiWorker implements AuthApiWorker {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthApiWorker.class);

    private final AuthApi api;

    public DefaultAuthApiWorker(AuthApi api) {
        this.api = api;
    }

    @Override
    public Single<Response<DataToken>> register() {
        return api.register()
                .map(ResponseConverter::convert);
    }
}
