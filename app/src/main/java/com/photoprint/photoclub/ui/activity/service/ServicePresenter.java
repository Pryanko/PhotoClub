package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.ui.activity.category.CategoryPresenter;
import com.photoprint.photoclub.ui.activity.service.adapter.ServiceListAdapter;
import com.photoprint.photoclub.data.interactor.ServiceLoader;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ServicePresenter extends BaseMvpViewStatePresenter<ServiceView, ServiceViewState> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryPresenter.class);

    private List<Service> services = Collections.emptyList();

    private final Navigator navigator;
    private final ServiceListAdapter serviceListAdapter;
    private final ServiceLoader serviceLoader;
    private final ServiceParams serviceParams;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    ServicePresenter(ServiceViewState viewState,
                     Navigator navigator,
                     ServiceListAdapter serviceListAdapter,
                     ServiceLoader serviceLoader,
                     ServiceParams serviceParams) {
        super(viewState);
        this.navigator = navigator;
        this.serviceListAdapter = serviceListAdapter;
        this.serviceLoader = serviceLoader;
        this.serviceParams = serviceParams;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = serviceLoader.getServicesByCategoryId(serviceParams.getCategoryId())
                .subscribeOn(AppSchedulers.db())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        this.services = result.getServices();
                        serviceListAdapter.setServices(this.services);
                    } else {
                        //Обработка пустого списка
                    }
                }, logger::error);

    }

    public void onBackBtnClicked() {
        logger.trace("onBackBtnClicked");
        navigator.navigateBack();
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }

    public void onServiceClicked(int position) {
        logger.trace("onServiceClicked: " + position);
    }
}
