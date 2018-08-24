package com.photoprint.photoclub.data.api.mapper;

import com.photoprint.photoclub.model.Guide;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
public interface GuideMapper {

    GuideMapper INSTANCE = Mappers.getMapper(GuideMapper.class);

    Guide entityToModel(com.photoprint.network.api.model.manual.Guide entity);

    List<Guide> entityListToModelList(List<com.photoprint.network.api.model.manual.Guide> entities);
}
