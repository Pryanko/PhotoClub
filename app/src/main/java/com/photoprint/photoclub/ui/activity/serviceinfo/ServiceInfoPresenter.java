package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.model.Maquette;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class ServiceInfoPresenter extends BaseMvpViewStatePresenter<ServiceInfoView, ServiceInfoViewState> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInfoPresenter.class);

    private final Navigator navigator;

    private Maquette maquette = null;
    /**
     * Флажок, отображается ли в данный момент фрагмент с выбором макетов
     */
    private boolean maquetteListVisible = false;

    @Inject
    ServiceInfoPresenter(ServiceInfoViewState viewState,
                         Navigator navigator) {
        super(viewState);
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
    }

    public void onBackBtnClicked() {
        if (maquetteListVisible) {
            maquetteListVisible = false;
            view.hideMaquetteList();
        } else {
            navigator.navigateBack();
        }
    }

    public void onClickSelectMaquetteBtn() {
        logger.trace("onClickSelectMaquetteBtn");
        maquetteListVisible = true;
        view.showMaquetteList();
    }

    public void onMaquetteItemClicked(Maquette maquette) {
        logger.trace("onMaquetteItemClicked");
        this.maquette = maquette;
        view.hideMaquetteList();
        view.setMaquetteName(this.maquette.getName());
    }
}
