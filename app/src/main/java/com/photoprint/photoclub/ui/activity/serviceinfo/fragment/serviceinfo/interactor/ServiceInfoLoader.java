package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.repository.ServiceRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик инфо для услуги
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoLoader {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoLoader.class);

    private final ServiceRepository serviceRepository;

    @Inject
    ServiceInfoLoader(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Single<Result> getServiceById(long serviceId) {
        return Single.fromCallable(() -> serviceRepository.getServiceById(serviceId))
                .map(service -> new Result(UserError.NO_ERROR, service))
//                .onErrorReturn(throwable -> {
//                   return new Result(userErrorBuilder.fromThrowable(throwable), null);
//                })
                .subscribeOn(AppSchedulers.db());

    }

    public static class Result {

        private final UserError userError;
        private final Service service;

        Result(UserError userError,
               Service service) {
            this.userError = userError;
            this.service = service;
        }


        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public Service getService() {
            return service;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
