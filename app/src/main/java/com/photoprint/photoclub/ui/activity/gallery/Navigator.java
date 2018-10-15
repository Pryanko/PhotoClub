package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;
import com.photoprint.photoclub.ui.activity.base.BaseNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator extends BaseNavigator<GalleryActivity> {

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        super(activityNavigator);
    }

    @Override
    public void onResume(GalleryActivity activity) {
        baseActivity = activity;
    }

    @Override
    public void onPause() {
        baseActivity = null;
    }
}
