package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.room.entity.ServiceEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper(uses = ServiceTypeMapper.class)
public interface ServiceMapper extends BaseMapper<Service, ServiceEntity> {
}
