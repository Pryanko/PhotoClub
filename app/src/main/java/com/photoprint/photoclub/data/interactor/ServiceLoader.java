package com.photoprint.photoclub.data.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.repository.ServiceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик услуг
 *
 * @author Grigoriy Pryamov.
 */
public class ServiceLoader {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoader.class);

    private final ServiceRepository serviceRepository;

    @Inject
    ServiceLoader(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Single<Result> getServicesByCategoryId(long categoryId) {
        return Single
                .fromCallable(() -> serviceRepository.getServicesByCategoryId(categoryId))
                .map(services -> new Result(UserError.NO_ERROR, services))
//                .onErrorReturn(throwable -> {
//                    UserError userError = userErrorBuilder.fromThrowable(throwable);
//                    return new Result(userError, Collections.emptyList(), true);
//                })
                .subscribeOn(AppSchedulers.db());
    }


    public static class Result {

        private final UserError userError;
        private final List<Service> services;

        Result(UserError userError, List<Service> services) {
            this.userError = userError;
            this.services = services;
        }


        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<Service> getServices() {
            return services;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
