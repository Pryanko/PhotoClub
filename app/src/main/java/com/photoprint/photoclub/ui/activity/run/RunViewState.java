package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
class RunViewState extends BaseMvpViewState<RunView> implements RunView {

    private boolean buttonNextVisible;
    private boolean loading;

    @Inject
    RunViewState() {
    }

    @Override
    protected void onViewAttached(RunView view) {
        view.setBtnVisible(buttonNextVisible);
        view.setLoading(loading);
    }

    @Override
    protected void onViewDetached(RunView view) {

    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
        forEachView(view -> view.setLoading(loading));
    }

    @Override
    public void setBtnVisible(boolean visible) {
        this.buttonNextVisible = visible;
        forEachView(view -> view.setBtnVisible(visible));
    }
}
