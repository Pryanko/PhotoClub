package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.interactor.LocalImagesProvider;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Maquette;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoPresenter extends BaseMvpViewStatePresenter<ServiceInfoView, ServiceInfoViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoPresenter.class);

    private final Navigator navigator;
    private final ApiWorker apiWorker;
    private final DbTransaction dbTransaction;
    private final LocalImagesProvider localImagesProvider;
    private final LocalImageRepository localImageRepository;

    private Disposable loadDisposable = Disposables.disposed();

    private Maquette maquette = null;
    /**
     * Флажок, отображается ли в данный момент фрагмент с выбором макетов
     */
    private boolean maquetteListVisible = false;

    @Inject
    ServiceInfoPresenter(ServiceInfoViewState viewState,
                         Navigator navigator,
                         ApiWorker apiWorker,
                         LocalImagesProvider localImagesProvider,
                         LocalImageRepository localImageRepository,
                         DbTransaction dbTransaction) {
        super(viewState);
        this.navigator = navigator;
        this.apiWorker = apiWorker;
        this.localImagesProvider = localImagesProvider;
        this.localImageRepository = localImageRepository;
        this.dbTransaction = dbTransaction;
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
        view.setMaquetteName(this.maquette.getName());
    }

    void onNextBtnClicked() {
        loadDisposable = localImagesProvider.getLocalImagesRx()
                .doOnSubscribe(disposable -> view.setLoading(true))
                .doOnSuccess(localImages -> dbTransaction.callInTx(() -> localImageRepository.insert(localImages)))
//                .flatMap(localImages -> apiWorker.createOrder())
//                .observeOn(AppSchedulers.network())
//                .doOnSuccess(singleDataResponse -> logger.trace(singleDataResponse.get().getData().getOrderId().toString()))
                .toCompletable()
                .subscribeOn(AppSchedulers.db())
                .observeOn(AppSchedulers.ui())
                .subscribe(() -> view.setLoading(false), logger::error);
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }
}
