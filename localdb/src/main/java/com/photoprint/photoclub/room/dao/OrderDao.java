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
    Long getCountOrders();
}
