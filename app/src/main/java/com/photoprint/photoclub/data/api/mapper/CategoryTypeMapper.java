package com.photoprint.photoclub.data.api.mapper;

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
}
