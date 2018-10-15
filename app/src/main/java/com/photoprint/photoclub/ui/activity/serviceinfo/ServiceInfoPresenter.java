package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.interactor.LocalImagesProvider;
import com.photoprint.photoclub.data.interactor.OrderManager;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Maquette;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoPresenter extends BaseMvpViewStatePresenter<ServiceInfoView, ServiceInfoViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoPresenter.class);

    private final Navigator navigator;
    private final OrderManager orderManager;
    private final DbTransaction dbTransaction;
    private final ServiceInfoParams serviceInfoParams;
    private final LocalImagesProvider localImagesProvider;
    private final LocalImageRepository localImageRepository;

    private Disposable loadDisposable = Disposables.disposed();

    private Maquette maquette = null;
    /**
     * Флажок, выбран ли макет
     */
    private boolean isMaquetteActive;
    /**
     * Флажок, отображается ли в данный момент фрагмент с выбором макетов
     */
    private boolean maquetteListVisible = false;

    @Inject
    ServiceInfoPresenter(ServiceInfoViewState viewState,
                         Navigator navigator,
                         OrderManager orderManager,
                         LocalImagesProvider localImagesProvider,
                         LocalImageRepository localImageRepository,
                         DbTransaction dbTransaction,
                         ServiceInfoParams serviceInfoParams) {
        super(viewState);
        this.navigator = navigator;
        this.orderManager = orderManager;
        this.localImagesProvider = localImagesProvider;
        this.localImageRepository = localImageRepository;
        this.dbTransaction = dbTransaction;
        this.serviceInfoParams = serviceInfoParams;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    void onBackBtnClicked() {
        if (maquetteListVisible) {
            maquetteListVisible = false;
            view.hideMaquetteList();
        } else {
            navigator.navigateBack();
        }
    }

    void onClickSelectMaquetteBtn() {
        logger.trace("onClickSelectMaquetteBtn");
        maquetteListVisible = true;
        view.showMaquetteList();
    }

    void onMaquetteItemClicked(Maquette maquette) {
        logger.trace("onMaquetteItemClicked");
        this.maquette = maquette;
        view.hideMaquetteList();
        maquetteListVisible = false;
        isMaquetteActive = true;
        view.setMaquetteName(this.maquette.getName());
    }

    void onNextBtnClicked() {
        if (isMaquetteActive) {
            loadDisposable = Maybe
                    .create(emitter -> {
                        if (orderManager.containsActiveOrder()) {
                            logger.trace("Active order exists");
                            emitter.onComplete();
                        } else {
                            logger.trace("Active order does not exist");
                            emitter.onSuccess(true);
                        }
                    })
                    .doOnSubscribe(disposable -> view.setLoading(true))
                    .flatMapObservable(aBoolean -> localImagesProvider.getLocalImagesRx())
                    .doOnNext(localImages -> dbTransaction.callInTx(() -> {
                        localImageRepository.deleteAll();
                        localImageRepository.insert(localImages);
                        logger.trace("Count local images: " + localImages.size());
                    }))
                    .flatMapCompletable(localImages -> orderManager.create(serviceInfoParams.getServiceId()))
                    .observeOn(AppSchedulers.network())
                    .subscribeOn(AppSchedulers.db())
                    .observeOn(AppSchedulers.ui())
                    .subscribe(() -> {
                        view.setLoading(false);
                        navigator.navigateToGalleryActivity();
                    }, logger::error);
        } else {

        }
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }
}
