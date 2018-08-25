package com.photoprint.photoclub.ui.activity.category.adapter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Category;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface CategoryListAdapter {
    void setCategories(@NonNull List<Category> categories);
}
