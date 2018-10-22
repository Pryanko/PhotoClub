package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ImageSettingCardPresenter extends BaseMvpViewStatePresenter<ImageSettingCardView, ImageSettingCardViewState> {

    @Inject
    public ImageSettingCardPresenter(ImageSettingCardViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {

    }
}
