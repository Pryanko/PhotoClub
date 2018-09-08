package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsViewState extends BaseMvpViewState<ServiceSettingsView> implements ServiceSettingsView {

    private String image;
    private String serviceName;
    private String price;

    @Inject
    ServiceSettingsViewState() {
    }

    @Override
    protected void onViewAttached(ServiceSettingsView view) {
        view.setImage(image);
        view.setServiceName(serviceName);
        view.setServicePrice(price);
    }

    @Override
    protected void onViewDetached(ServiceSettingsView view) {

    }

    @Override
    public void setImage(String image) {
        this.image = image;
        forEachView(view -> view.setImage(this.image));
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
        forEachView(view -> view.setServiceName(this.serviceName));
    }

    @Override
    public void setServicePrice(String price) {
        this.price = price;
        forEachView(view -> view.setServicePrice(this.price));
    }
}
