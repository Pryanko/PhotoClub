package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.ServiceEntity;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface ServiceDao extends BaseDao<ServiceEntity> {
    /**
     * Метод запрашивающий все услуги из БД
     */
    @Query("SELECT * FROM Service")
    List<ServiceEntity> getServices();

    @Query("SELECT * FROM Service WHERE categoryId = :categoryId")
    List<ServiceEntity> getServicesByCategoryId(int categoryId);

    @Query("SELECT * FROM Service WHERE id = :serviceId")
    ServiceEntity getServiceById(int serviceId);

    @Query("DELETE FROM Service")
    void deleteAll();
}
