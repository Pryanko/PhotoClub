package com.photoprint.photoclub.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.category.CategoryActivity;

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

    public void navigateToCategoryActivity(Activity activity) {
        Intent intent = CategoryActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        animForward(activity);
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
