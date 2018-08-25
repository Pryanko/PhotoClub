package com.photoprint.photoclub.ui.activity.category.adapter;

import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.ui.adapter.base.BaseDiffUtilCallback;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class DiffUtilCallback extends BaseDiffUtilCallback<Category> {


    public DiffUtilCallback(List<Category> oldItems, List<Category> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean areItemsTheSame(Category oldItem, Category newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Category oldItem, Category newItem) {
        return oldItem.equals(newItem);
    }
}
