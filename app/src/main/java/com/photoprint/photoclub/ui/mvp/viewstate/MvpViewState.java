package com.photoprint.photoclub.ui.mvp.viewstate;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * Состояние {@link MvpView}.
 *
 * @param <V> {@link MvpView}
 * @author Grigoriy Pryamov.
 */
public interface MvpViewState<V extends MvpView> {
    /**
     * Присоединение view.
     *
     * @param view Присоединенная view
     */
    void attachView(V view);

    /**
     * Отсоединение view.
     *
     * @param view Отсоединенная view
     */
    void detachView(V view);
}
