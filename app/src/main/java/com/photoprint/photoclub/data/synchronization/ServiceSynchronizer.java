package com.photoprint.photoclub.data.synchronization;

import android.support.annotation.NonNull;

import com.photoprint.utils.ListUtils;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.services.Service;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.api.mapper.ServiceMapper;
import com.photoprint.photoclub.data.synchronization.base.BaseSynchronizer;
import com.photoprint.photoclub.repository.ServiceRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class ServiceSynchronizer extends BaseSynchronizer<Service> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSynchronizer.class);

    private final ServiceRepository serviceRepository;
    ServiceMapper mapper = ServiceMapper.INSTANCE;

    @Inject
    ServiceSynchronizer(ApiWorker apiWorker,
                        DbTransaction transaction,
                        ServiceRepository serviceRepository) {
        super(apiWorker, transaction);
        this.serviceRepository = serviceRepository;
    }

    @Override
    protected Single<Response<Data<Service>>> getApiSource() {
        logger.trace("Service sync: Start");
        return apiWorker.getServices();
    }

    @Override
    protected List<Service> store(@NonNull List<Service> entities) {
        if(ListUtils.notEmpty(entities)) {
            serviceRepository.deleteAll();
            serviceRepository.insert(mapper.entityListToModelList(entities));
            logger.trace("Service store - success");
        }

        return entities;
    }
}
