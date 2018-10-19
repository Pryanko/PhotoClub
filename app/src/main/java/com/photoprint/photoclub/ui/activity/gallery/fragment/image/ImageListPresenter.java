package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ImageListPresenter extends BaseMvpViewStatePresenter<ImageListView, ImageListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ImageListPresenter.class);

    @Inject
    ImageListPresenter(ImageListViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
