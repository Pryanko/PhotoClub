package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
class ServiceInfoFragmentViewState extends BaseMvpViewState<ServiceInfoFragmentView> implements ServiceInfoFragmentView {

    private String serviceImage;
    private String description;
    private boolean selectMaquetteError;

    @Inject
    ServiceInfoFragmentViewState() {
    }

    @Override
    protected void onViewAttached(ServiceInfoFragmentView view) {
        view.setServiceImage(serviceImage);
        view.setServiceDescription(description);
        view.selectMaquetteErrorEnabled(selectMaquetteError);
    }

    @Override
    protected void onViewDetached(ServiceInfoFragmentView view) {

    }

    @Override
    public void setServiceImage(String image) {
        this.serviceImage = image;
        forEachView(view -> view.setServiceImage(this.serviceImage));
    }

    @Override
    public void setServiceDescription(String description) {
        this.description = description;
        forEachView(view -> view.setServiceDescription(this.description));
    }

    @Override
    public void selectMaquetteErrorEnabled(boolean error) {
        this.selectMaquetteError = error;
        forEachView(view -> view.selectMaquetteErrorEnabled(this.selectMaquetteError));
    }
}
