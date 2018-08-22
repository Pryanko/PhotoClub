package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.CategoryEntity;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface CategoryDao extends BaseDao<CategoryEntity> {
    /**
     * Метод запрашивающий все категории услуг из БД
     */
    @Query("SELECT * FROM Category")
    List<CategoryEntity> getCategories();

    @Query("DELETE FROM Category")
    void deleteAll();
}
