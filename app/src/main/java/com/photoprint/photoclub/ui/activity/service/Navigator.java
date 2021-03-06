package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<ServiceActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    public void navigateBack() {
        forSafeAction(() -> activityNavigator.navigateBack(baseActivity));
    }

    public void navigateToServiceInfoActivity(ServiceInfoParams serviceInfoParams) {
        forSafeAction(() -> activityNavigator.navigateToServiceInfoActivity(baseActivity, serviceInfoParams));
    }

    @Override
    public void onResume(ServiceActivity activity) {
        this.baseActivity = activity;
    }

    @Override
    public void onPause() {
        this.baseActivity = null;
    }
}
