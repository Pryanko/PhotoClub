
package com.photoprint.photoclub.impl;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.room.dao.LocalImageDao;
import com.photoprint.photoclub.room.entity.LocalImageEntity;
import com.photoprint.photoclub.room.mapper.LocalImageMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class LocalImageRepositoryImpl extends BaseRepositoryImpl<LocalImage, LocalImageEntity, Long> implements LocalImageRepository {

    private final LocalImageMapper mapper;

    @Inject
    LocalImageRepositoryImpl(AppDatabase appDatabase,
                             LocalImageMapper mapper) {
        super(appDatabase);
        this.mapper = mapper;
    }

    @Override
    protected LocalImageMapper mapper() {
        return mapper;
    }

    @Override
    protected LocalImageDao dao() {
        return appDatabase.localImageDao();
    }

    @NonNull
    @Override
    public List<LocalImage> getImagesByFolder(String folder) {
        return mapper.entityListToModelList(dao().getImagesByFolder(folder));
    }
}
