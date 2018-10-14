package com.photoprint.photoclub.repository;

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
    List<Order> getOrders();

    /**
     * Метод возвращающий кол-во локальных заказов
     */
    Long getCountOrders();
}
