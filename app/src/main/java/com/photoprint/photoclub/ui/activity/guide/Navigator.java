package com.photoprint.photoclub.ui.activity.guide;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<GuideActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    @Override
    public void onResume(GuideActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPause() {
        activity = null;
    }

    public void navigateToCategoryActivity() {
        activityNavigator.navigateToCategoryActivity(activity);
    }

    public void navigateBack() {
        activityNavigator.navigateBack(activity);
    }
}
