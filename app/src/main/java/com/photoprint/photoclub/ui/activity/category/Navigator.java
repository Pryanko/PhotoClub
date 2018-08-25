package com.photoprint.photoclub.ui.activity.category;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator {

    private CategoryActivity activity;
    private final ActivityNavigator activityNavigator;

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        this.activityNavigator = activityNavigator;
    }

    public void onResume(CategoryActivity activity) {
        this.activity = activity;
    }

    public void onPause() {
        this.activity = null;
    }
}
