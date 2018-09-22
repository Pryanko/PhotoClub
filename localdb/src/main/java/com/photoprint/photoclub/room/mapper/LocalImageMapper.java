package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.room.entity.LocalImageEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface LocalImageMapper extends BaseMapper<LocalImage, LocalImageEntity> {
}
