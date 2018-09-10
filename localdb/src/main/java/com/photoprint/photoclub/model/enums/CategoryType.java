package com.photoprint.photoclub.model.enums;

import android.support.annotation.NonNull;

/**
 * Типы категорий
 *
 * @author Grigoriy Pryamov.
 */
public enum CategoryType {
    /**
     * Неизвестный тип
     */
    UNKNOWN("unknown"),
    /**
     * Печать фотографий
     */
    PHOTO("photo"),
    /**
     * Магниты
     */
    MAGNETS("magnets"),
    /**
     * Печать на холсте
     */
    HOLST("holst"),
    /**
     * Футболки
     */
    SHORTS("shorts"),
    /**
     * Кружки
     */
    CUPS("cups");

    private final String code;

    CategoryType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @NonNull
    public static CategoryType getByCode(String code) {
        for (CategoryType categoryType : values()) {
            if (categoryType.code.equals(code)) {
                return categoryType;
            }
        }
        return UNKNOWN;
    }
}
