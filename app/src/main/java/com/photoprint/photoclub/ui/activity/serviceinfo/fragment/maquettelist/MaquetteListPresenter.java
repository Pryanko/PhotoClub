package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class MaquetteListPresenter extends BaseMvpViewStatePresenter<MaquetteListView, MaquetteListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(MaquetteListPresenter.class);

    @Inject
    MaquetteListPresenter(MaquetteListViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }
}
