package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter.ImageListAdapter;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter.ImageListAdapterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class ImageListModule {

    @Binds
    abstract ImageListAdapter imageListAdapter(ImageListAdapterImpl imageListAdapter);

}