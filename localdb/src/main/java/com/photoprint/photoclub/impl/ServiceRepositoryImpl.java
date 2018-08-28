package com.photoprint.photoclub.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.repository.ServiceRepository;
import com.photoprint.photoclub.room.dao.ServiceDao;
import com.photoprint.photoclub.room.entity.ServiceEntity;
import com.photoprint.photoclub.room.mapper.ServiceMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class ServiceRepositoryImpl extends BaseRepositoryImpl<Service, ServiceEntity, Long> implements ServiceRepository {

    private final ServiceMapper mapper;

    @Inject
    ServiceRepositoryImpl(AppDatabase appDatabase,
                          ServiceMapper serviceMapper) {
        super(appDatabase);
        this.mapper = serviceMapper;
    }

    @Override
    protected ServiceMapper mapper() {
        return mapper;
    }

    @Override
    protected ServiceDao dao() {
        return appDatabase.serviceDao();
    }

    @NonNull
    @Override
    public List<Service> getServices() {
        return mapper.entityListToModelList(dao().getServices());
    }

    @NonNull
    @Override
    public List<Service> getServicesByCategoryId(long categoryId) {
        return mapper.entityListToModelList(dao().getServicesByCategoryId(categoryId));
    }

    @Nullable
    @Override
    public Service getServiceById(int serviceId) {
        return mapper.entityToModel(dao().getServiceById(serviceId));
    }

    @Override
    public void deleteAll() {
        dao().deleteAll();
    }
}
