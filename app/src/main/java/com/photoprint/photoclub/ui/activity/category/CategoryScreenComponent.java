package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface CategoryScreenComponent {

    CategoryComponent.Builder categoryComponentBuilder();
}
