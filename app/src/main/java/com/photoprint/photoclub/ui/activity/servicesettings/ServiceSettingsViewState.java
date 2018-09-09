package com.photoprint.photoclub.ui.activity.servicesettings;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsViewState extends BaseMvpViewState<ServiceSettingsView> implements ServiceSettingsView {

    private String image;
    private String serviceName;
    private String price;
    private List<String> serviceNameList = Collections.emptyList();
    private List<String> serviceTypeList = Collections.emptyList();

    @Inject
    ServiceSettingsViewState() {
    }

    @Override
    protected void onViewAttached(ServiceSettingsView view) {
        view.setImage(image);
        view.setServiceName(serviceName);
        view.setServicePrice(price);
        view.setServiceNameList(serviceNameList);
        view.setServiceTypeList(serviceTypeList);
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

    @Override
    public void setServiceNameList(@NonNull List<String> serviceNameList) {
        this.serviceNameList = serviceNameList;
        forEachView(view -> view.setServiceNameList(serviceNameList));
    }

    @Override
    public void setServiceTypeList(@NonNull List<String> serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
        forEachView(view -> view.setServiceTypeList(serviceTypeList));
    }
}
