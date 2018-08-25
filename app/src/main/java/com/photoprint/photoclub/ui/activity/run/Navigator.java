package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<RunActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    public void navigateToGuideActivity() {
        activityNavigator.navigateToGuideActivity(activity);
    }

    public void navigateToCategoryActivity() {
        activityNavigator.navigateToCategoryActivity(activity);
    }

    @Override
    public void onResume(RunActivity runActivity) {
        this.activity = runActivity;
    }

    @Override
    public void onPause() {
        this.activity = null;
    }
}
