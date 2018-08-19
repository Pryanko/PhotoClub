package com.photoprint.photoclub.ui.activity.base;

import android.os.Bundle;

import com.photoprint.photoclub.ui.mvp.MvpDelegate;
import com.photoprint.photoclub.ui.mvp.view.MvpView;


/**
 * Базовая активити с нормальной реализацией биндинга {@link MvpView}.
 *
 * @author Aleksandr Brazhkin
 */
public abstract class MvpActivity extends BaseActivity {

    /**
     * Делегат для хранения/получения тега
     */
    private MvpDelegate mvpDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpDelegate = new MvpDelegate(appComponent().mvpProcessor(), (MvpView) this);
        mvpDelegate.init(savedInstanceState);
    }

    public MvpDelegate getMvpDelegate() {
        return mvpDelegate;
    }

    @Override
    public void onStart() {
        super.onStart();
        mvpDelegate.bindView();
    }

    @Override
    public void onStop() {
        mvpDelegate.unbindView();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate.saveState(outState);
    }

    @Override
    public void onDestroy() {
        mvpDelegate.destroy(keepAlive());
        super.onDestroy();
    }

    protected boolean keepAlive() {
        return !isFinishing();
    }
}
