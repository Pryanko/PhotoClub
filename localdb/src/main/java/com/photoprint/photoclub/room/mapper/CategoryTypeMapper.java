package com.photoprint.photoclub.room.mapper;

import com.photoprint.photoclub.model.enums.CategoryType;

import org.mapstruct.Mapper;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public abstract class CategoryTypeMapper {

    public CategoryType entityToModel(String entity) {
        return entity == null ? CategoryType.UNKNOWN : CategoryType.getByCode(entity);
    }

    public String modelToEntity(CategoryType model) {
        return model == CategoryType.UNKNOWN ? null : model.getCode();
    }
}
