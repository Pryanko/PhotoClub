package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoViewState extends BaseMvpViewState<ServiceInfoView> implements ServiceInfoView {

    private boolean maquetteListVisible;

    @Inject
    ServiceInfoViewState() {
    }

    @Override
    protected void onViewAttached(ServiceInfoView view) {
        view.setMaquetteFragmentVisible(maquetteListVisible);
    }

    @Override
    protected void onViewDetached(ServiceInfoView view) {

    }

    @Override
    public void setMaquetteFragmentVisible(boolean fragmentVisible) {

    }

    @Override
    public void showMaquetteList() {
        this.maquetteListVisible = true;
        forEachView(ServiceInfoView::showMaquetteList);
    }

    @Override
    public void hideMaquetteList() {
        this.maquetteListVisible = false;
        forEachView(ServiceInfoView::hideMaquetteList);
    }
}
