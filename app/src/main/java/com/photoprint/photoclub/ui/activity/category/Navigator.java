package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<CategoryActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    public void navigateToServiceActivity(ServiceParams serviceParams) {
        forSafeAction(() -> activityNavigator.navigateToServiceActivity(baseActivity, serviceParams));
    }

    public void navigateToServiceSettingsActivity(ServiceSettingsParams serviceSettingsParams) {
        forSafeAction(() -> activityNavigator.navigateToServiceSettingsActivity(baseActivity, serviceSettingsParams));
    }

    public void navigateBack() {
        forSafeAction(() -> activityNavigator.navigateBack(baseActivity));
    }

    public void onResume(CategoryActivity activity) {
        this.baseActivity = activity;
    }

    public void onPause() {
        this.baseActivity = null;
    }
}
