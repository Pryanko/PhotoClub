package com.photoprint.photoclub.ui.activity.guide;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.ui.activity.guide.interactor.GuideLoader;
import com.photoprint.photoclub.ui.activity.guide.model.GuideParams;
import com.photoprint.photoclub.ui.mvp.presenter.BaseMvpViewStatePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * @author Grigoriy Pryamov.
 */
public class GuidePresenter extends BaseMvpViewStatePresenter<GuideView, GuideViewState> {

    private static final Logger logger = LoggerFactory.getLogger(GuidePresenter.class);

    private List<Guide> guides = Collections.emptyList();
    private int pagePosition = 0;

    private final GuideParams guideParams;
    private final GuideLoader guideLoader;
    private final Navigator navigator;
    private Disposable loadDisposable = Disposables.disposed();

    @Inject
    GuidePresenter(GuideViewState viewState,
                   GuideParams guideParams,
                   GuideLoader guideLoader,
                   Navigator navigator) {
        super(viewState);
        this.guideParams = guideParams;
        this.guideLoader = guideLoader;
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        changeBackPageButtonEnabled();
        loadDisposable = guideLoader.getGuides()
                .subscribeOn(AppSchedulers.db())
                .observeOn(AppSchedulers.ui())
                .subscribe(result -> {
                    if (result.isSuccessful()) {
                        this.guides = result.getguides();
                        view.setGuides(this.guides);
                        view.setCurrentPage(pagePosition);
                    } else {
                        //Обработка пустого списка
                    }
                }, logger::error);
    }

    @Override
    public void destroy() {
        loadDisposable.dispose();
        super.destroy();
    }

    public void onNextPageBtnClicked() {
        logger.trace("onNextPageBtnClicked");
        pagePosition++;
        view.setCurrentPage(pagePosition);
        changeButtonsVisible();
        changeBackPageButtonEnabled();
    }

    public void onBackPageBtnClicked() {
        logger.trace("onBackPageBtnClicked");
        pagePosition--;
        view.setCurrentPage(pagePosition);
        changeButtonsVisible();
        changeBackPageButtonEnabled();
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
        navigator.navigateToCategoryActivity();
    }

    public void onCloseBtnClicked() {
        logger.trace("onCloseBtnClicked");
        navigator.navigateBack();
    }

    public void setCurrentPagePosition(int currentPagePosition) {
        this.pagePosition = currentPagePosition;
        changeButtonsVisible();
        changeBackPageButtonEnabled();
    }

    private void changeButtonsVisible() {
        //В параметрах для гайда передаём булеву, определяющая с какого экрана перешел пользователь
        if (guideParams.isNavigateFromMenu()) {
            if (isEndPagePosition()) {
                view.setCloseButtonVisible(true);
            } else {
                view.setCloseButtonVisible(false);
            }
        } else {
            if (isEndPagePosition()) {
                view.setNextButtonVisible(true);
            } else {
                view.setNextButtonVisible(false);
            }
        }
    }

    private boolean isEndPagePosition() {
        return pagePosition == guides.size() - 1;
    }

    private void changeBackPageButtonEnabled() {
        if (pagePosition == 0) {
            view.setBackPageButtonEnabled(false);
        } else {
            view.setBackPageButtonEnabled(true);
        }
    }
}
