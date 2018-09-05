package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsPresenter extends BaseMvpViewStatePresenter<ServiceSettingsView, ServiceSettingsViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSettingsPresenter.class);

    private final Navigator navigator;

    @Inject
    ServiceSettingsPresenter(ServiceSettingsViewState viewState,
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
