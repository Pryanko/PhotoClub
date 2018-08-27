package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceViewState extends BaseMvpViewState<ServiceView> implements ServiceView {

    @Inject
    ServiceViewState() {
    }

    @Override
    protected void onViewAttached(ServiceView view) {

    }

    @Override
    protected void onViewDetached(ServiceView view) {

    }
}
