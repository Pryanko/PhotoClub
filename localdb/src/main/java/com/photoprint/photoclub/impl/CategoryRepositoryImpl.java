package com.photoprint.photoclub.impl;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.repository.CategoryRepository;
import com.photoprint.photoclub.room.dao.CategoryDao;
import com.photoprint.photoclub.room.entity.CategoryEntity;
import com.photoprint.photoclub.room.mapper.CategoryMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, CategoryEntity, Long> implements CategoryRepository {

    private final CategoryMapper mapper;

    @Inject
    CategoryRepositoryImpl(AppDatabase appDatabase,
                           CategoryMapper categoryMapper) {
        super(appDatabase);
        this.mapper = categoryMapper;
    }

    @Override
    protected CategoryMapper mapper() {
        return mapper;
    }

    @Override
    protected CategoryDao dao() {
        return appDatabase.categoryDao();
    }

    @NonNull
    @Override
    public List<Category> getCategories() {
        return mapper.entityListToModelList(dao().getCategories());
    }

    @Override
    public void deleteAll() {
        dao().deleteAll();
    }
}
