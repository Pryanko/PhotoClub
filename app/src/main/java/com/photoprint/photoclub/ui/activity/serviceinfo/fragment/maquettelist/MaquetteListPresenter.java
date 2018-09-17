package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter.MaquetteListAdapter;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.interactor.MaquetteListLoader;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class MaquetteListPresenter extends BaseMvpViewStatePresenter<MaquetteListView, MaquetteListViewState> {

    private static final Logger logger = LoggerFactory.getLogger(MaquetteListPresenter.class);

    private final MaquetteListAdapter maquetteListAdapter;
    private final MaquetteListLoader maquetteListLoader;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    MaquetteListPresenter(MaquetteListViewState viewState,
                          MaquetteListAdapter maquetteListAdapter,
                          MaquetteListLoader maquetteListLoader) {
        super(viewState);
        this.maquetteListAdapter = maquetteListAdapter;
        this.maquetteListLoader = maquetteListLoader;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = maquetteListLoader.getMaquettes()
                .subscribeOn(AppSchedulers.background())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    maquetteListAdapter.setMaquettes(result.getMaquettes());
                });
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }
}
