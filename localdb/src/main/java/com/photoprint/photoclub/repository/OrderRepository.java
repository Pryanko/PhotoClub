package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Order;
import com.photoprint.photoclub.repository.base.BaseRepository;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface OrderRepository extends BaseRepository<Order, Long> {

    /**
     * Метод возвращающий все заказы
     */
    @NonNull
    List<Order> getOrders();

    /**
     * Метод возвращающий кол-во локальных заказов
     */
    @NonNull
    Long getCountOrders();

    /**
     * Метод проверяющий есть ли сейчас активный заказ
     */
    boolean containsActiveOrder();
}
