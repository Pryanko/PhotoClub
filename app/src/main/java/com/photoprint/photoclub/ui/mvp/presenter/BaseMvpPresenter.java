package com.photoprint.photoclub.ui.mvp.presenter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * Скелетная реализация {@link MvpPresenter}.
 *
 * @param <V> {@link MvpView}, c которой работает презентер.
 * @author Grigoriy Pryamov.
 */
public abstract class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {
    /**
     * Представление, ассоциированное с презентером.
     */
    protected V view;
    /**
     * Флаг, что презентер проинициализирован, т.е. начал свою работу
     */
    private boolean initialized = false;

    public void initialize() {
        if (!initialized) {
            initialized = true;
            onInitialize();
        }
    }

    protected abstract void onInitialize();

    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void bind(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void unbind(@NonNull V view) {
        this.view = null;
    }

    @Override
    public void destroy() {

    }
}
