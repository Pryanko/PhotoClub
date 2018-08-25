package com.photoprint.photoclub.ui.adapter.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс адаптера
 *
 * @author Grigoriy Pryamov.
 */
public abstract class BaseItemsRecyclerAdapter <T, VH extends RecyclerView.ViewHolder> extends BaseRecyclerAdapter<VH> {

    protected List<T> items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Nullable
    public T getItem(int position) {
        if (position >= 0 && position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(@NonNull List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void insertItem(int position, T item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void insertItems(int position, @NonNull List<T> items) {
        this.items.addAll(position, items);
        notifyItemRangeInserted(position, items.size());
    }

    public void appendItems(List<T> items) {
        insertItems(this.items.size(), items);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }
}
