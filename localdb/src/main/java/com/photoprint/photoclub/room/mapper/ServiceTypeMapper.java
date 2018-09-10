package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.enums.ServiceType;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public abstract class ServiceTypeMapper {

    public ServiceType entityToModel(Integer entity) {
        return entity == null ? null : ServiceType.valueOf(entity);
    }

    public Integer modelToEntity(ServiceType model) {
        return model == null ? null : model.getCode();
    }
}
