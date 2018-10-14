package com.photoprint.photoclub.data.api.mapper;

import com.photoprint.photoclub.model.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "orderId"),
            @Mapping(target = "serviceId", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "confirmed", ignore = true)
    })
    Order entityToModel(com.photoprint.network.api.model.order.Order entity);
}
