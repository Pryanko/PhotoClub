package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<ServiceSettingsActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    public void navigateBack() {
        activityNavigator.navigateBack(activity);
    }

    @Override
    public void onResume(ServiceSettingsActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPause() {
        this.activity = null;
    }
}
