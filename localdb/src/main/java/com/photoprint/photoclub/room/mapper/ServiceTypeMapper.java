package com.photoprint.photoclub.room.mapper;

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

    public Integer modelToEntity(ServiceType model) {
        if (model == null) {
            return null;
        }
        return model.getCode();
    }
}
