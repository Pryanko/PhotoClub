package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;
import com.photoprint.photoclub.ui.activity.guide.model.GuideParams;

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

    public void navigateToGuideActivity(GuideParams guideParams) {
        forSafeAction(() -> activityNavigator.navigateToGuideActivity(baseActivity, guideParams));
    }

    public void navigateToCategoryActivity() {
        forSafeAction(() -> activityNavigator.navigateToCategoryActivity(baseActivity));
    }

    @Override
    public void onResume(RunActivity runActivity) {
        this.baseActivity = runActivity;
    }

    @Override
    public void onPause() {
        this.baseActivity = null;
    }
}
