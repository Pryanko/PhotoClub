package com.photoprint.photoclub.data.api.mapper;

import com.photoprint.photoclub.model.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper(uses = {CategoryTypeMapper.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category entityToModel(com.photoprint.network.api.model.category.Category entity);

    List<Category> entityListToModelList(List<com.photoprint.network.api.model.category.Category> entities);
}
