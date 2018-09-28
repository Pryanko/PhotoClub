package com.photoprint.photoclub.ui.activity.base.delegate;

import android.Manifest;
import android.app.Activity;

import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class RtPermissionDelegate extends BaseRtPermisionsDelegate {

    @Inject
    RtPermissionDelegate(Activity activity, ActivityNavigator activityNavigator) {
        super(activity, activityNavigator);
    }

    @Override
    protected String getPermissionName() {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    @Override
    protected int getRationaleLight() {
        return R.string.storage_permission_rationale_light;
    }

    @Override
    protected int getRationaleHard() {
        return R.string.storage_permission_rationale_light_hard;
    }
}
