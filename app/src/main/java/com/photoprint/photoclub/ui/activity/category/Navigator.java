package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;

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
        activityNavigator.navigateToServiceActivity(activity, serviceParams);
    }

    public void navigateBack() {
        activityNavigator.navigateBack(activity);
    }

    public void onResume(CategoryActivity activity) {
        this.activity = activity;
    }

    public void onPause() {
        this.activity = null;
    }
}
