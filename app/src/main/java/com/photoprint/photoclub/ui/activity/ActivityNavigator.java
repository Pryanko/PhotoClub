package com.photoprint.photoclub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.category.CategoryActivity;
import com.photoprint.photoclub.ui.activity.gallery.GalleryActivity;
import com.photoprint.photoclub.ui.activity.guide.GuideActivity;
import com.photoprint.photoclub.ui.activity.guide.model.GuideParams;
import com.photoprint.photoclub.ui.activity.service.ServiceActivity;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;
import com.photoprint.photoclub.ui.activity.serviceinfo.ServiceInfoActivity;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;
import com.photoprint.photoclub.ui.activity.servicesettings.ServiceSettingsActivity;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;

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

    public void navigateToServiceActivity(Activity activity, ServiceParams serviceParams) {
        Intent intent = ServiceActivity.getCallingIntent(activity, serviceParams);
        activity.startActivity(intent);
        animForward(activity);
    }

    public void navigateToServiceSettingsActivity(Activity activity, ServiceSettingsParams serviceSettingsParams) {
        Intent intent = ServiceSettingsActivity.getCallingIntent(activity, serviceSettingsParams);
        activity.startActivity(intent);
        animForward(activity);
    }

    public void navigateToServiceInfoActivity(Activity activity, ServiceInfoParams serviceInfoParams) {
        Intent intent = ServiceInfoActivity.getCallingIntent(activity, serviceInfoParams);
        activity.startActivity(intent);
        animForward(activity);
    }

    public void navigateToCategoryActivity(Activity activity) {
        Intent intent = CategoryActivity.getCallingIntent(activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        animForward(activity);
    }

    public void navigateToGuideActivity(Activity activity, GuideParams guideParams) {
        Intent intent = GuideActivity.getCallingIntent(activity, guideParams);
        activity.startActivity(intent);
        animForward(activity);
    }

    public void navigateToGalleryActivity(Activity activity) {
        Intent intent = GalleryActivity.getCallingIntent(activity);
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

    public void navigateToAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
