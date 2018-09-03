package com.photoprint.photoclub.ui.activity.guide;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GuideViewState extends BaseMvpViewState<GuideView> implements GuideView {

    private List<Guide> guides = Collections.emptyList();
    private int pagePosition;
    private boolean nextButtonVisible;
    private boolean backPageButtonEnabled;
    private boolean closeButtonVisible;

    @Inject
    GuideViewState() {
    }

    @Override
    protected void onViewAttached(GuideView view) {
        view.setGuides(guides);
        view.setCurrentPage(pagePosition);
        view.setNextButtonVisible(nextButtonVisible);
        view.setBackPageButtonEnabled(backPageButtonEnabled);
        view.setCloseButtonVisible(closeButtonVisible);
    }

    @Override
    protected void onViewDetached(GuideView view) {

    }

    @Override
    public void setGuides(@NonNull List<Guide> guides) {
        this.guides = guides;
        forEachView(view -> view.setGuides(this.guides));
    }

    @Override
    public void setCurrentPage(int positionPage) {
        this.pagePosition = positionPage;
        forEachView(view -> view.setCurrentPage(this.pagePosition));
    }

    @Override
    public void setNextButtonVisible(boolean visible) {
        this.nextButtonVisible = visible;
        forEachView(view -> view.setNextButtonVisible(this.nextButtonVisible));
    }

    @Override
    public void setBackPageButtonEnabled(boolean enabled) {
        this.backPageButtonEnabled = enabled;
        forEachView(view -> view.setBackPageButtonEnabled(this.backPageButtonEnabled));
    }

    @Override
    public void setCloseButtonVisible(boolean visible) {
        this.closeButtonVisible = visible;
        forEachView(view -> view.setCloseButtonVisible(closeButtonVisible));
    }
}
