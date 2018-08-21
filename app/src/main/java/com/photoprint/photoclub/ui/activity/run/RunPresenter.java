package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.auth.AuthManager;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class RunPresenter extends BaseMvpViewStatePresenter<RunView, RunViewState> {

    private static final Logger logger = LoggerFactory.getLogger(RunPresenter.class);

    private final AuthManager authManager;
    private Disposable loadDisposable = Disposables.disposed();
    private Disposable registerDisposable = Disposables.disposed();

    @Inject
    RunPresenter(RunViewState viewState,
                 AuthManager authManager) {
        super(viewState);
        this.authManager = authManager;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        registration();
    }

    /**
     * Метот инициализирующий регистрацию устроства на сервере
     */
    private void registration() {
        registerDisposable = authManager
                .register()
                .observeOn(AppSchedulers.ui())
                .doOnSubscribe(disposable -> view.setLoading(true))
                .subscribe(() -> {
                    view.setLoading(false);
                    view.setBtnVisible(true);
                });
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        registerDisposable.dispose();
        super.destroy();
    }
}
