package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ImageSettingCardPresenter extends BaseMvpViewStatePresenter<ImageSettingCardView, ImageSettingCardViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ImageSettingCardPresenter.class);

    @Inject
    ImageSettingCardPresenter(ImageSettingCardViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    void onNextBtnClicked() {

    }

    void onAddCountBtnClicked() {

    }

    void onCutCountBtnClicked() {

    }
}
