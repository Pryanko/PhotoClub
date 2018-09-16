package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoFragmentPresenter extends BaseMvpViewStatePresenter<ServiceInfoFragmentView, ServiceInfoFragmentViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoFragmentPresenter.class);

    @Inject
    ServiceInfoFragmentPresenter(ServiceInfoFragmentViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }
}
