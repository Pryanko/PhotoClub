package com.photoprint.photoclub.data.interactor;

import io.reactivex.Completable;

/**
 * Управление состоянием заказа
 *
 * @author Grigoriy Pryamov.
 */
public interface OrderManager {
    /**
     * Метод создающий заказ
     */
    Completable create(long serviceId);

    /**
     * Метод проверяющий существует ли на данный момент активный заказ
     */
    boolean containsActiveOrder();

}
