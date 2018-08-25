package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface CategoryComponent {

    void inject(CategoryActivity categoryActivity);

    CategoryPresenter categoryPresenter();

    @Subcomponent.Builder
    interface Builder {
        CategoryComponent.Builder activityModule(ActivityModule activityModule);

        CategoryComponent build();
    }
}
