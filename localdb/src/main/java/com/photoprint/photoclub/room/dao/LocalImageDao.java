package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.LocalImageEntity;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface LocalImageDao extends BaseDao<LocalImageEntity> {

    @Query("SELECT * FROM LocalImage WHERE parentFolder = :folder")
    List<LocalImageEntity> getImagesByFolder(String folder);

    @Query("SELECT DISTINCT * FROM LocalImage GROUP BY parentFolder")
    List<LocalImageEntity> getFolders();

    @Query("DELETE FROM LocalImage")
    void deleteAll();
}
