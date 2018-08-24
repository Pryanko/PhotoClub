package com.photoprint.photoclub.impl;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.repository.GuideRepository;
import com.photoprint.photoclub.room.dao.GuideDao;
import com.photoprint.photoclub.room.entity.GuideEntity;
import com.photoprint.photoclub.room.mapper.GuideMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class GuideRepositoryImpl extends BaseRepositoryImpl<Guide, GuideEntity, Long> implements GuideRepository {

    private final GuideMapper mapper;

    @Inject
    GuideRepositoryImpl(AppDatabase appDatabase,
                                  GuideMapper mapper) {
        super(appDatabase);
        this.mapper = mapper;
    }

    @Override
    protected GuideMapper mapper() {
        return mapper;
    }

    @Override
    protected GuideDao dao() {
        return appDatabase.guideDao();
    }

    @NonNull
    @Override
    public List<Guide> getGuides() {
        return mapper.entityListToModelList(dao().getGuides());
    }

    @Override
    public void deleteAll() {
        dao().deleteAll();
    }
}
