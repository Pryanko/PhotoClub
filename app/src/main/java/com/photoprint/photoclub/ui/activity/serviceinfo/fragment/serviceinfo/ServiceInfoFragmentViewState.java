package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
class ServiceInfoFragmentViewState extends BaseMvpViewState<ServiceInfoFragmentView> implements ServiceInfoFragmentView {

    private String serviceImage;

    @Inject
    ServiceInfoFragmentViewState() {
    }

    @Override
    protected void onViewAttached(ServiceInfoFragmentView view) {
        view.setServiceImage(this.serviceImage);
    }

    @Override
    protected void onViewDetached(ServiceInfoFragmentView view) {

    }

    @Override
    public void setServiceImage(String image) {
        this.serviceImage = image;
        forEachView(view -> view.setServiceImage(this.serviceImage));
    }
}
