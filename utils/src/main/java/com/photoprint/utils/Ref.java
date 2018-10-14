package com.photoprint.utils;

import android.support.annotation.Nullable;

/**
 * Класс-холдер для nullable объекта.
 * Используется в основном для RxJava2, т.к. там запрещено
 * возвращение null-объектов в цепочке.
 *
 * @author Aleksandr Brazhkin
 */
public class Ref<T> {

    private static final Ref NULL_REF = new Ref<>(null);

    @SuppressWarnings("unchecked")
    public static <T> Ref<T> nullRef() {
        return (Ref<T>) NULL_REF;
    }

    private final T value;

    public Ref(@Nullable T value) {
        this.value = value;
    }

    @Nullable
    public T get() {
        return value;
    }

}