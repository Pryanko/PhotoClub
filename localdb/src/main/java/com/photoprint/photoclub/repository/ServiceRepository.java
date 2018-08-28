package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.repository.base.BaseRepository;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface ServiceRepository extends BaseRepository<Service, Long> {
    /**
     * Возврщащет список всех услуг
     */
    @NonNull
    List<Service> getServices();

    /**
     * Возращает список услуг для категории
     *
     * @param categoryId Id категории
     * @return услуги принадлежищие к категории
     */
    @NonNull
    List<Service> getServicesByCategoryId(long categoryId);

    /**
     * Возращает услугу по id
     *
     * @param serviceId Id услуги
     * @return услугу по id
     */
    @Nullable
    Service getServiceById(int serviceId);

    /**
     * Метод удаляющий все категории
     */
    void deleteAll();
}
