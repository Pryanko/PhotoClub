package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<ServiceInfoActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    public void navigateBack() {
        forSafeAction(() -> activityNavigator.navigateBack(baseActivity));
    }

    @Override
    public void onResume(ServiceInfoActivity activity) {
        baseActivity = activity;
    }

    @Override
    public void onPause() {
        baseActivity = null;
    }
}
