package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.User;
import com.photoprint.photoclub.room.entity.UserEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface UserMapper extends BaseMapper<User, UserEntity> {
}
