package com.photoprint.photoclub.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.base.DbTransactionImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Основной di-модуль локальной БД
 *
 * @author Grigoriy Pryamov.
 */
@Module(includes = {RepositoryModule.class, MapperModule.class})
public class LocalDatabaseModule {

    /**
     * Имя файла БД
     */
    private static final String DB_NAME = "local.db";

    @Provides
    @Singleton
    public AppDatabase appDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                //.allowMainThreadQueries()
                //.addMigrations(migrationProvider.getMigrations())
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public DbTransaction dbTransaction(AppDatabase appDatabase) {
        return new DbTransactionImpl(appDatabase);
    }
}
