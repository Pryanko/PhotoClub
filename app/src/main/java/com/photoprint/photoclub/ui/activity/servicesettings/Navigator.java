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
        forSafeAction(() -> activityNavigator.navigateBack(baseActivity));
    }

    @Override
    public void onResume(ServiceSettingsActivity activity) {
        this.baseActivity = activity;
    }

    @Override
    public void onPause() {
        this.baseActivity = null;
    }
}
