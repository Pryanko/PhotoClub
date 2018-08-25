package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent(modules = {CategoryModule.class})
public interface CategoryScreenComponent {

    CategoryComponent.Builder categoryComponentBuilder();
}
