package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.activity.category.CategoryPresenter;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServicePresenter extends BaseMvpViewStatePresenter<ServiceView, ServiceViewState> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryPresenter.class);

    private final Navigator navigator;

    @Inject
    ServicePresenter(ServiceViewState viewState,
                     Navigator navigator) {
        super(viewState);
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {

    }

    public void onBackBtnClicked() {
        logger.trace("onBackBtnClicked");
        navigator.navigateBack();
    }
}
