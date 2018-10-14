package com.photoprint.photoclub.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.photoprint.photoclub.room.entity.base.EntityWithId;

/**
 * Модель заказа для таблички
 *
 * @author Grigoriy Pryamov.
 */
@Entity(tableName = "LocalOrder")
public class OrderEntity implements EntityWithId<Long> {
    /**
     * Id заказа
     */
    @PrimaryKey
    private long id;
    /**
     * Id услуги
     */
    private long serviceId;
    /**
     * Активный ли заказа (открытый/закрытый)
     */
    private boolean active;
    /**
     * Подтвержденный ли заказ (валидация на сервере)
     */
    private boolean confirmed;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
