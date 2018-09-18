package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo.interactor.ServiceInfoLoader;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;
import com.photoprint.photoclub.ui.activity.servicesettings.Navigator;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoFragmentPresenter extends BaseMvpViewStatePresenter<ServiceInfoFragmentView, ServiceInfoFragmentViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoFragmentPresenter.class);

    private final Navigator navigator;
    private final ServiceInfoParams serviceInfoParams;
    private final ServiceInfoLoader serviceInfoLoader;

    private Disposable loadDisposable = Disposables.disposed();

    /**
     * Выбранный пользователем макет для итема услуги
     */
    // TODO: 18.09.2018 затащить полную модель маккета
    private String maquetteName = null;

    @Inject
    ServiceInfoFragmentPresenter(ServiceInfoFragmentViewState viewState,
                                 Navigator navigator,
                                 ServiceInfoParams serviceInfoParams,
                                 ServiceInfoLoader serviceInfoLoader) {
        super(viewState);
        this.navigator = navigator;
        this.serviceInfoParams = serviceInfoParams;
        this.serviceInfoLoader = serviceInfoLoader;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = serviceInfoLoader.getServiceById(serviceInfoParams.getServiceId())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        view.setServiceImage(result.getService().getImage480());
                        view.setServiceDescription(result.getService().getDescription());
                    }
                }, logger::error);
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
        if (maquetteName == null) {
            view.selectMaquetteErrorEnabled(true);
        }
    }

    public void maquetteSelected(String maquetteName) {
        this.maquetteName = maquetteName;
        view.selectMaquetteErrorEnabled(false);
    }
}
