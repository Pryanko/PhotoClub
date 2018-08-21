package com.photoprint.photoclub.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.UserEntity;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
@Dao
public interface UserDao extends BaseDao<UserEntity> {

    @Query("SELECT * FROM User")
    UserEntity getCurrent();

    @Query("SELECT * FROM User")
    Single<UserEntity> getCurrentRx();

    @Query("DELETE FROM User")
    void deleteAll();
}
