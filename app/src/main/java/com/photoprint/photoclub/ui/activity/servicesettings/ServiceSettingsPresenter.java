package com.photoprint.photoclub.ui.activity.servicesettings;

import android.util.LongSparseArray;

import com.example.utils.ListUtils;
import com.example.utils.Preconditions;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.interactor.ServiceLoader;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceSettingsPresenter extends BaseMvpViewStatePresenter<ServiceSettingsView, ServiceSettingsViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSettingsPresenter.class);

    private List<String> serviceNames = new ArrayList<>();
    private LongSparseArray<Service> serviceArray = new LongSparseArray<>();

    private final Navigator navigator;
    private final ServiceLoader serviceLoader;
    private final ServiceSettingsParams serviceSettingsParams;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    ServiceSettingsPresenter(ServiceSettingsViewState viewState,
                             Navigator navigator,
                             ServiceLoader serviceLoader,
                             ServiceSettingsParams serviceSettingsParams) {
        super(viewState);
        this.navigator = navigator;
        this.serviceLoader = serviceLoader;
        this.serviceSettingsParams = serviceSettingsParams;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = serviceLoader.getServicesByCategoryId(serviceSettingsParams.getCategoryId())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        if (ListUtils.notEmpty(result.getServices())) {
                            createServiceNameList(result.getServices());
                            Service firstService = serviceArray.valueAt(0);
                            changeViewApply(firstService);
                            view.setServiceNameList(serviceNames);

                            List<String> serviceTypeList = new ArrayList<>();
                            serviceTypeList.add("Глянцевая");
                            serviceTypeList.add("Маттовая");
                            view.setServiceTypeList(serviceTypeList);
                        }
                    }
                });
    }

    private void createServiceNameList(List<Service> services) {
        for (Service service : services) {
            Preconditions.checkNotNull(service);
            serviceNames.add(service.getName());
            addServiceToList(service);
        }
    }

    private void addServiceToList(Service service) {
        serviceArray.put(service.getId(), service);
    }

    public void onBackBtnClicked() {
        navigator.navigateBack();
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }

    public void onFormatItemClicked(int position) {
        Service service = serviceArray.valueAt(position);
        changeViewApply(service);
    }

    public void onTypeItemClicked(int position) {
        logger.trace("onTypeItemClicked: " + position);
    }

    private void changeViewApply(Service service) {
        view.setImage(service.getImage480());
        view.setServiceName(service.getName());
        view.setServicePrice(service.getPrice());
    }

    public void onOptionSwitchClicked(boolean isChecked) {
        logger.trace("onOptionSwitchClicked " + isChecked);
    }

    public void onNextButtonClicked() {
        logger.trace("onNextButtonClicked");
    }
}
