package com.photoprint.network.auth;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.photoprint.network.ApiConfig;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Grigoriy Pryamov.
 */
public class AuthRetrofitBuilder {

    /**
     * Конфигурация API.
     */
    private final ApiConfig apiConfig;
    /**
     * Билдер http-клиента
     */
    private final AuthHttpClientBuilder authHttpClientBuilder;

    @Inject
    AuthRetrofitBuilder(ApiConfig apiConfig, AuthHttpClientBuilder authHttpClientBuilder) {
        this.apiConfig = apiConfig;
        this.authHttpClientBuilder = authHttpClientBuilder;
    }

    /**
     * Создает клиент ретрофит
     */
    public Retrofit build() {
        OkHttpClient client = authHttpClientBuilder.build();
        return new Retrofit.Builder()
                .baseUrl(apiConfig.getUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
