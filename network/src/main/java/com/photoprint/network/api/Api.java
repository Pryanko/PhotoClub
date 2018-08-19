package com.photoprint.network.api;

import com.photoprint.network.api.model.category.Category;
import com.photoprint.network.api.model.manual.Manual;
import com.photoprint.network.api.model.services.DataService;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    Observable<Manual> getManuals();

    /**
     * Метод для получения категорий услуг
     *
     * @return категории услуг
     */
    @GET("category")
    Observable<Category> getCategory();

    /**
     * Метод получающий услугу по id категории услуги
     *
     * @param categoryId id категории
     */
    @GET("service")
    Observable<DataService> getService(@Query("category_id") int categoryId);

    /**
     * Метод для получения описания услуги
     *
     * @param serviceId id услуги
     * @return
     */
    @GET("service")
    Observable<DataService> getDescriptionService(@Query("id") int serviceId);
}
