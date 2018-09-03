package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.ui.activity.guide.model.GuideParams;
import com.photoprint.photoclub.ui.activity.run.interactor.RunInitializer;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class RunPresenter extends BaseMvpViewStatePresenter<RunView, RunViewState> {

    private static final Logger logger = LoggerFactory.getLogger(RunPresenter.class);

    private final RunInitializer runInitializer;
    private final Navigator navigator;
    private Disposable initDisposable = Disposables.disposed();

    @Inject
    RunPresenter(RunViewState viewState,
                 RunInitializer runInitializer,
                 Navigator navigator) {
        super(viewState);
        this.runInitializer = runInitializer;
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        init();
    }

    /**
     * Метод инициализирующий регистрацию устройства и синхронизацию данных на сервере
     */
    private void init() {
        initDisposable = runInitializer.init()
                .subscribeOn(AppSchedulers.network())
                .observeOn(AppSchedulers.ui())
                .doOnSubscribe(disposable -> view.setLoading(true))
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        view.setLoading(false);
                        view.setBtnVisible(true);
                    }
                }, logger::error);
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
        GuideParams guideParams = new GuideParams();
        guideParams.setNavigateFromMenu(false);
        navigator.navigateToGuideActivity(guideParams);
    }

    @Override
    public void destroy() {
        initDisposable.dispose();
        super.destroy();
    }
}
