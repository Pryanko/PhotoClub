package com.photoprint.photoclub.model.base;

/**
 * @author Grigoriy Pryamov.
 */
public interface ModelWithId<Id> {

    /**
     *Вощвращет Id
     */
    Id getId();
    /**
     * Устанавливает Id
     */
    void setId(Id id);
}
