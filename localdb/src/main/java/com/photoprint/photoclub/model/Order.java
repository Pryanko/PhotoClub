package com.photoprint.photoclub.model;

import com.photoprint.photoclub.model.base.ModelWithId;

/**
 * Модель заказа
 *
 * @author Grigoriy Pryamov.
 */
public class Order implements ModelWithId<Long> {
    /**
     * Id заказа
     */
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
