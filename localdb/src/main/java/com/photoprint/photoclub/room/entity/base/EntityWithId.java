package com.photoprint.photoclub.room.entity.base;

/**
 * Модель локальной БД с Id.
 *
 * @author Grigoriy Pryamov.
 */
public interface EntityWithId<Id> {

    /**
     * Возвращает Id
     */
    Id getId();

    /**
     * Устанавливает Id
     */
    void setId(Id id);
}
