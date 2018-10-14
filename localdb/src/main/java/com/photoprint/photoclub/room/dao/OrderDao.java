package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.OrderEntity;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface OrderDao extends BaseDao<OrderEntity> {
    /**
     * Метод запрашивающий все заказы
     */
    @Query("SELECT * FROM localorder")
    List<OrderEntity> getOrders();

    /**
     * Метод возвращающий кол-во локальных заказов
     */
    @Query("SELECT COUNT(*) FROM localorder")
    long getCountOrders();

    /**
     * Метод проверяющий в БД - существуют ли активные заказы
     */
    @Query("SELECT COUNT(*) FROM localorder WHERE active = 1")
    long containsActiveOrder();
}
