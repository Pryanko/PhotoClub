package com.photoprint.photoclub.ui.activity.servicesettings;

import android.util.LongSparseArray;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.interactor.LocalImagesProvider;
import com.photoprint.photoclub.data.interactor.OrderManager;
import com.photoprint.photoclub.data.interactor.ServiceLoader;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;
import com.photoprint.utils.ListUtils;
import com.photoprint.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
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
    private final OrderManager orderManager;
    private final DbTransaction dbTransaction;
    private final ServiceLoader serviceLoader;
    private final LocalImagesProvider localImagesProvider;
    private final LocalImageRepository localImageRepository;
    private final ServiceSettingsParams serviceSettingsParams;

    private Disposable loadDisposable = Disposables.disposed();
    private Disposable orderCreateDisposable = Disposables.disposed();
    /**
     * Сервис id для создания заказа
     */
    private long currentServiceId;
    /**
     * Разрешение на считывание данных
     */
    private boolean isStoragePermission;

    @Inject
    ServiceSettingsPresenter(ServiceSettingsViewState viewState,
                             Navigator navigator,
                             ServiceLoader serviceLoader,
                             OrderManager orderManager,
                             DbTransaction dbTransaction,
                             LocalImagesProvider localImagesProvider,
                             LocalImageRepository localImageRepository,
                             ServiceSettingsParams serviceSettingsParams) {
        super(viewState);
        this.navigator = navigator;
        this.serviceLoader = serviceLoader;
        this.orderManager = orderManager;
        this.dbTransaction = dbTransaction;
        this.localImagesProvider = localImagesProvider;
        this.localImageRepository = localImageRepository;
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

    void onBackBtnClicked() {
        navigator.navigateBack();
    }

    void onFormatItemClicked(int position) {
        Service service = serviceArray.valueAt(position);
        changeViewApply(service);
    }

    void onTypeItemClicked(int position) {
        logger.trace("onTypeItemClicked: " + position);
    }

    private void changeViewApply(Service service) {
        currentServiceId = service.getId();
        view.setImage(service.getImage480());
        view.setServiceName(service.getName());
        view.setServicePrice(service.getPrice());
    }

    void onOptionSwitchClicked(boolean isChecked) {
        logger.trace("onOptionSwitchClicked " + isChecked);
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
        if (isStoragePermission) {
            orderCreateDisposable = Maybe
                    .create(emitter -> {
                        if (orderManager.containsActiveOrder()) {
                            logger.trace("Active order exists");
                            emitter.onComplete();
                        } else {
                            logger.trace("Active order does not exist");
                            emitter.onSuccess(true);
                        }
                    })
                    .flatMapObservable(aBoolean -> localImagesProvider.getLocalImagesRx())
                    .doOnNext(localImages -> dbTransaction.callInTx(() -> {
                        localImageRepository.deleteAll();
                        localImageRepository.insert(localImages);
                        logger.trace("Count local images: " + localImages.size());
                    }))
                    .flatMapCompletable(localImages -> orderManager.create(currentServiceId))
                    .observeOn(AppSchedulers.network())
                    .subscribeOn(AppSchedulers.db())
                    .observeOn(AppSchedulers.ui())
                    .doOnSubscribe(disposable -> view.showLoading(true))
                    .subscribe(() -> {
                        view.showLoading(false);
                        navigator.navigateToGalleryActivity();
                    }, logger::error);
        } else {
            view.showDialogForPermissions();
        }
    }

    void onHideLoading() {
        view.showLoading(false);
    }

    void onPermissionRequestFinished(boolean granted) {
        logger.trace("granted " + granted);
        this.isStoragePermission = granted;
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        orderCreateDisposable.dispose();
        super.destroy();
    }
}
