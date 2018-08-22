package com.photoprint.network.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.photoprint.network.ApiConfig;
import com.photoprint.network.auth.HttpClientBuilder;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Grigoriy Pryamov.
 */
public class ApiRetrofitBuilder {

    /**
     * Конфигурация API.
     */
    private final ApiConfig apiConfig;
    /**
     * Билдер http-клиента
     */
    private final HttpClientBuilder httpClientBuilder;

    @Inject
    ApiRetrofitBuilder(ApiConfig apiConfig, HttpClientBuilder httpClientBuilder) {
        this.apiConfig = apiConfig;
        this.httpClientBuilder = httpClientBuilder;
    }

    /**
     * Создает клиент ретрофит
     */
    public Retrofit build() {
        OkHttpClient client = httpClientBuilder.build();
        return new Retrofit.Builder()
                .baseUrl(apiConfig.getUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
