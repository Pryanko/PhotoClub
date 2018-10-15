package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GalleryPresenter extends BaseMvpViewStatePresenter<GalleryView, GalleryViewState> {

    private static final Logger logger = LoggerFactory.getLogger(GalleryPresenter.class);

    @Inject
    public GalleryPresenter(GalleryViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {

    }
}
