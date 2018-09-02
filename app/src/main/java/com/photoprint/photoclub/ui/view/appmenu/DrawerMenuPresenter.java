package com.photoprint.photoclub.ui.view.appmenu;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuParams;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class DrawerMenuPresenter extends BaseMvpViewStatePresenter<DrawerMenuMvpView, DrawerMenuViewState> {

    private static final Logger logger = LoggerFactory.getLogger(DrawerMenuPresenter.class);
    private DrawerMenuParams drawerMenuParams;

    @Inject
    DrawerMenuPresenter(DrawerMenuViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        applyMainDrawerParams();
    }

    public void setParams(DrawerMenuParams params) {
        this.drawerMenuParams = params;
        applyMainDrawerParams();
    }

    private void applyMainDrawerParams() {
        if (!isInitialized() || drawerMenuParams == null) {
            return;
        }
        view.setSelectedItem(drawerMenuParams.getSelectedItem());
    }
}
