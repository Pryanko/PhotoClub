package com.photoprint.photoclub.model.enums;

import android.support.annotation.NonNull;

/**
 * Тип услуги
 *
 * @author Grigoriy Pryamov.
 */
public enum ServiceType {

    /**
     * Неизвестный тип
     */
    UNKNOWN(-1),
    /**
     * Тип обычной услуги
     */
    NORMAL(0),
    /**
     * Тип составной улслуги
     */
    COMPOSITE(1);

    public final int code;

    ServiceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @NonNull
    public static ServiceType valueOf(int code) {
        for (ServiceType serviceType : values()) {
            if (serviceType.code == code) {
                return serviceType;
            }
        }
        return UNKNOWN;
    }
}
