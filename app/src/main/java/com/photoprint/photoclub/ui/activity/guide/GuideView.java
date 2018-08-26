package com.photoprint.photoclub.ui.activity.guide;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.ui.mvp.view.MvpView;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface GuideView extends MvpView {

    void setGuides(@NonNull List<Guide> guides);

    void setCurrentPage(int positionPage);

    void setNextButtonVisible(boolean visible);

    void setBackPageButtonEnabled(boolean enabled);
}
