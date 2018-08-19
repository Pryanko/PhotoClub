package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class RunPresenter extends BaseMvpViewStatePresenter<RunView, RunViewState> {

    private static final Logger logger = LoggerFactory.getLogger(RunPresenter.class);

    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    RunPresenter(RunViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        loadDisposable = Completable
                .timer(10, TimeUnit.SECONDS)
                .doOnSubscribe(disposable -> view.setLoading(true))
                .observeOn(AndroidSchedulers.mainThread())
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
        super.destroy();
    }
}
