package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.photoclub.di.FragmentScope;
import com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard.ImageSettingCardComponent;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@FragmentScope
@Subcomponent(modules = ImageListModule.class)
public interface ImageListComponent {

    void inject(ImageListFragment imageListFragment);

    ImageListPresenter imageListPresenter();

    ImageSettingCardComponent imageSettingCardComponent();
}
