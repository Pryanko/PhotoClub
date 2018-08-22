package com.photoprint.network;

import com.photoprint.network.api.Api;
import com.photoprint.network.api.ApiRetrofitBuilder;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.DefaultApiWorker;
import com.photoprint.network.auth.AuthApi;
import com.photoprint.network.auth.AuthApiWorker;
import com.photoprint.network.auth.AuthRetrofitBuilder;
import com.photoprint.network.auth.DefaultAuthApiWorker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Di модуль для Api
 *
 * @author Grigoriy Pryamov.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    AuthApiWorker authApiWorker(AuthRetrofitBuilder authRetrofitBuilder) {
        Retrofit retrofit = authRetrofitBuilder.build();
        AuthApi authApi = retrofit.create(AuthApi.class);
        return new DefaultAuthApiWorker(authApi);
    }

    @Provides
    @Singleton
    ApiWorker apiWorker(ApiRetrofitBuilder apiRetrofitBuilder) {
        Retrofit retrofit = apiRetrofitBuilder.build();
        Api api = retrofit.create(Api.class);
        return new DefaultApiWorker(api);
    }
}
