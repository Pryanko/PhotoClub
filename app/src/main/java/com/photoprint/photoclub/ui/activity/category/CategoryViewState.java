package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class CategoryViewState extends BaseMvpViewState<CategoryView> implements CategoryView {

    @Inject
    CategoryViewState() {
    }

    @Override
    protected void onViewAttached(CategoryView view) {

    }

    @Override
    protected void onViewDetached(CategoryView view) {

    }
}
