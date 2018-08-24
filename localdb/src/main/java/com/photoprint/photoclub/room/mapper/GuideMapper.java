package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.room.entity.GuideEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface GuideMapper extends BaseMapper<Guide, GuideEntity> {
}
