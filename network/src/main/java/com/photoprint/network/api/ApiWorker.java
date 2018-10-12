package com.photoprint.network.api;

import com.photoprint.network.Response;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.base.SingleData;
import com.photoprint.network.api.model.category.Category;
import com.photoprint.network.api.model.manual.Guide;
import com.photoprint.network.api.model.order.Order;
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
    Single<Response<Data<Category>>> getCategories();

    /**
     * Метод получающий услуги
     */
    Single<Response<Data<Service>>> getServices();

    /**
     * Метод создающий заказа для пользователя на сервере
     */
    Single<Response<SingleData<Order>>> createOrder();
}
