package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.Order;
import com.photoprint.photoclub.room.entity.OrderEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderEntity> {
}
