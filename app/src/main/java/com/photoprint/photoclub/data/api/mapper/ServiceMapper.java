package com.photoprint.photoclub.data.api.mapper;

import com.photoprint.photoclub.model.Service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper(uses = ServiceTypeMapper.class)
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    @Mappings({
            @Mapping(source = "multiply", target = "serviceType")
    })
    Service entityToModel(com.photoprint.network.api.model.services.Service entity);

    List<Service> entityListToModelList(List<com.photoprint.network.api.model.services.Service> entities);
}
