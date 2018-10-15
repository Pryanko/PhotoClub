package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent(modules = {GalleryModule.class})
public interface GalleryScreenComponent {

    GalleryComponent.Builder galleryComponentBuilder();
}
