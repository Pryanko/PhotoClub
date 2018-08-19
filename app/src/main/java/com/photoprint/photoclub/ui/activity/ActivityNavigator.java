package com.photoprint.photoclub.ui.activity;

import android.app.Activity;

import com.photoprint.photoclub.R;

import javax.inject.Inject;

/**
 * Основной класс для навигации в приложении
 *
 * @author Grigoriy Pryamov.
 */
public class ActivityNavigator {

    @Inject
    ActivityNavigator() {
    }

    public void navigateBack(Activity activity) {
        activity.finish();
        animBack(activity);
    }

    private void animForward(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.zero_animation);
    }

    private void animBack(Activity activity) {
        activity.overridePendingTransition(R.anim.zero_animation, R.anim.slide_to_right);
    }
}
