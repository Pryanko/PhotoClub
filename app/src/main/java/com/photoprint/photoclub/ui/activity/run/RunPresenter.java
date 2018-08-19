package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class RunPresenter extends BaseMvpViewStatePresenter<RunView, RunViewState> {

    private static final Logger logger = LoggerFactory.getLogger(RunPresenter.class);

    @Inject
    RunPresenter(RunViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
    }
}
