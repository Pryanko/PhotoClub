package com.photoprint.photoclub.data.api.mapper;

import com.photoprint.photoclub.model.enums.ServiceType;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public abstract class ServiceTypeMapper {

    public ServiceType entityToModel(Integer entity) {
        if (entity == null) {
            return null;
        }
        return ServiceType.valueOf(entity);
    }
}
