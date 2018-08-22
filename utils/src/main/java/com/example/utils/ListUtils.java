package com.example.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс со вспомогательными функциями для работы со списками.
 *
 * @author Aleksandr Brazhkin
 */
public class ListUtils {

    @NonNull
    public static long[] toPrimitives(@NonNull Collection<Long> collection) {
        int i = 0;
        long[] primitives = new long[collection.size()];
        for (Long aLong : collection) {
            primitives[i++] = aLong;
        }
        return primitives;
    }

    @NonNull
    public static List<Long> toBoxed(@NonNull long[] primitives) {
        int i = 0;
        List<Long> boxed = new ArrayList<>(primitives.length);
        for (Long aLong : primitives) {
            boxed.add(aLong);
        }
        return boxed;
    }

    /**
     * Проверяет, что позиция входит в границы списка.
     *
     * @param position Позиция
     * @param list     Список
     * @return {@code true} если по указанной позиции
     * можно запрашивать элемент, {@code false} иначе
     */
    public static boolean inRange(int position, List<?> list) {
        return position >= 0 && list.size() > position;
    }

    /**
     * Преобразует строку в список (разделитель ",")
     *
     * @param value     строка
     * @param converter конвертер элемента списка
     */
    public static <T> List<T> stringToList(@Nullable String value, Converter<String, T> converter) {
        return stringToList(value, ",", converter);
    }

    /**
     * Преобразует строку в список
     *
     * @param value     строка
     * @param delimiter разделитель
     * @param converter конвертер элемента списка
     */
    public static <T> List<T> stringToList(@Nullable String value, @NonNull String delimiter, Converter<String, T> converter) {
        if (value == null) {
            return null;
        }

        List<T> list = new ArrayList<>();

        if (value.equals("")) {
            return list;
        }

        String[] array = value.split(delimiter);
        for (String strValue : array) {
            if (strValue != null && !strValue.isEmpty()) {
                final T v = converter.convert(strValue);
                if (v != null) {
                    list.add(v);
                }
            }
        }
        return list;
    }

    /**
     * Преобразует список в строку (разделитель ",")
     *
     * @param list      список
     * @param converter конвертер элемента списка
     */
    public static <T> String listToString(@Nullable List<T> list, Converter<T, String> converter) {
        return listToString(list, ",", converter);
    }

    /**
     * Обратный методу isEmpty
     */
    public static <T> boolean notEmpty(@Nullable List<T> list) {
        return list == null || !list.isEmpty();
    }

    /**
     * Преобразует список в строку
     *
     * @param list      список
     * @param delimiter разделитель
     * @param converter конвертер элемента списка
     */
    public static <T> String listToString(@Nullable List<T> list, @NonNull String delimiter, Converter<T, String> converter) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            final T item = list.get(i);
            if (item != null) {
                final String itemStr = converter.convert(item);
                if (itemStr != null) {
                    if (b.length() != 0) {
                        b.append(delimiter);
                    }
                    b.append(itemStr);
                }
            }
        }
        return b.toString();
    }

    /**
     * Конвертер
     *
     * @param <S> Входной тип
     * @param <D> Выходной тип
     */
    public interface Converter<S, D> {
        D convert(S t);
    }
}
