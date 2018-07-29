package com.photoprint.photoclub.ui.mvp;

import com.photoprint.photoclub.ui.mvp.presenter.MvpPresenter;

/**
 * Фабрика презентеров.
 *
 * @author Grigoriy Pryamov.
 */
public interface PresenterProvider<P extends MvpPresenter> {
    P providePresenter();
}
