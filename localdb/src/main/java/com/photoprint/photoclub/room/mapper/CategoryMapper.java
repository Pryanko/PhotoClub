package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.room.entity.CategoryEntity;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper(uses = {CategoryTypeMapper.class})
public interface CategoryMapper extends BaseMapper<Category, CategoryEntity> {
}
