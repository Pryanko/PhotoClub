package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import com.photoprint.photoclub.di.CustomViewScope;
import com.photoprint.photoclub.ui.mvp.core.MvpProcessor;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@CustomViewScope
@Subcomponent
public interface ImageSettingCardComponent {

    ImageSettingCardPresenter imageSettingCardPresenter();

    MvpProcessor mvpProcessor();

    void inject(ImageSettingCard imageSettingCard);
}
