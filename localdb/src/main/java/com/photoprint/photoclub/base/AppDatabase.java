package com.photoprint.photoclub.base;

/**
 * Конфигурация локальной БД
 *
 * @author Grigoriy Pryamov.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.photoprint.localdb.BuildConfig;
import com.photoprint.photoclub.room.dao.UserDao;
import com.photoprint.photoclub.room.entity.CategoryEntity;
import com.photoprint.photoclub.room.entity.UserEntity;
import com.photoprint.photoclub.room.dao.CategoryDao;

@Database(version = BuildConfig.DB_VERSION, exportSchema = false,
        entities = {
                UserEntity.class,
                CategoryEntity.class

        })
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract CategoryDao categoryDao();
}
