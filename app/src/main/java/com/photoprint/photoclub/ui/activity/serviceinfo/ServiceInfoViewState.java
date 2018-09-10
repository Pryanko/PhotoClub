package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoViewState extends BaseMvpViewState<ServiceInfoView> implements ServiceInfoView {

    @Inject
    ServiceInfoViewState() {
    }

    @Override
    protected void onViewAttached(ServiceInfoView view) {

    }

    @Override
    protected void onViewDetached(ServiceInfoView view) {

    }
}
