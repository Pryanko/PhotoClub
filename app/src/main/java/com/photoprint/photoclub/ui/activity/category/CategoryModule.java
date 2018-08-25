package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.ui.activity.category.adapter.CategoryListAdapter;
import com.photoprint.photoclub.ui.activity.category.adapter.CategoryListAdapterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Grigoriy Pryamov.
 */
@Module
public abstract class CategoryModule {

    @Binds
    abstract CategoryListAdapter categoryListAdapter(CategoryListAdapterImpl categoryListAdapter);
}
