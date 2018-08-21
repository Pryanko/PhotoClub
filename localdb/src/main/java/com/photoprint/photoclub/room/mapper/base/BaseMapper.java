package com.photoprint.photoclub.room.mapper.base;

import java.util.List;

/**
 * Базовый маппер
 *
 * @author Grigoriy Pryamov.
 */
public interface BaseMapper<M, E>{

    M entityToModel(E entity);

    E modelToEntity(M model);

    List<M> entityListToModelList(List<E> entities);

    List<E> modelListToEntityList(List<M> models);
}
