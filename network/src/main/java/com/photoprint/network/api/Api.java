package com.photoprint.network.api;

import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.category.Category;
import com.photoprint.network.api.model.manual.Guide;
import com.photoprint.network.api.model.services.Service;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Network
 *
 * @author Grigoriy Pryamov.
 */
public interface Api {
    /**
     * Метод получающий данные для раздела обучения
     *
     * @return список итемов для экрана обучения
     */
    @GET("study")
    Single<Response<Data<Guide>>> getManuals();

    /**
     * Метод для получения категорий услуг
     *
     * @return категории услуг
     */
    @GET("category")
    Single<Response<Data<Category>>> getCategory();

    /**
     * Метод получающий услуги
     */
    @GET("service")
    Single<Response<Data<Service>>> getService();

//    /**
//     * Метод для получения описания услуги
//     *
//     * @param serviceId id услуги
//     * @return
//     */
//    @GET("service")
//    Observable<DataService> getDescriptionService(@Query("id") int serviceId);
}
