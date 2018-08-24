package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.GuideEntity;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface GuideDao extends BaseDao<GuideEntity> {

    /**
     * Метод запрашивающий все мануалы из БД
     */
    @Query("SELECT * FROM Guide")
    List<GuideEntity> getGuides();

    @Query("DELETE FROM Guide")
    void deleteAll();
}
