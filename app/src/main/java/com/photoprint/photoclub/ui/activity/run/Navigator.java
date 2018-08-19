package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator {

    private RunActivity activity;
    private final ActivityNavigator activityNavigator;

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        this.activityNavigator = activityNavigator;
    }

    public void onResume(RunActivity runActivity) {
        this.activity = runActivity;
    }

    public void onPause() {
        this.activity = null;
    }
}
