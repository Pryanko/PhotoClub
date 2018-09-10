package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoPresenter extends BaseMvpViewStatePresenter<ServiceInfoView, ServiceInfoViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoPresenter.class);

    private final Navigator navigator;

    @Inject
    ServiceInfoPresenter(ServiceInfoViewState viewState,
                         Navigator navigator) {
        super(viewState);
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    public void onBackBtnClicked() {
        navigator.navigateBack();
    }
}
