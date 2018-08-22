package com.photoprint.network.api;

import com.photoprint.network.Response;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.category.Category;
import com.photoprint.network.api.model.manual.Guide;
import com.photoprint.network.api.model.services.Service;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
public interface ApiWorker {

    /**
     * Метод получающий данные для раздела обучения
     *
     * @return список итемов для экрана обучения
     */
    Single<Response<Data<Guide>>> getManuals();

    /**
     * Метод для получения категорий услуг
     *
     * @return категории услуг
     */
    Single<Data<Category>> getCategory();

    /**
     * Метод получающий услуги
     */
    Single<Data<Service>> getService();
}
