package com.photoprint.photoclub.ui.adapter.base;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Базовый класс для реализаций {@link DiffUtil.Callback}.
 *
 * @author Aleksandr Brazhkin
 */
public abstract class BaseDiffUtilCallback<T> extends DiffUtil.Callback {

    private final List<T> oldItems;
    private final List<T> newItems;

    public BaseDiffUtilCallback(List<T> oldItems, List<T> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return getChangePayload(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
    }

    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    protected abstract boolean areContentsTheSame(T oldItem, T newItem);

    @Nullable
    protected Object getChangePayload(T oldItem, T newItem) {
        return null;
    }
}
