package com.photoprint.photoclub.ui.activity.guide;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GuidePresenter extends BaseMvpViewStatePresenter<GuideView, GuideViewState> {

    private static final Logger logger = LoggerFactory.getLogger(GuidePresenter.class);

    @Inject
    GuidePresenter(GuideViewState viewState) {
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
