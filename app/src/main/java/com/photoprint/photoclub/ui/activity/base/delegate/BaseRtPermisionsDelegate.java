package com.photoprint.photoclub.ui.activity.base.delegate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.ActivityNavigator;

/**
 * Базовый класс для делегатов для проверки Runtime Permissions.
 *
 * @author Grigoriy Pryamov.
 */
public abstract class BaseRtPermisionsDelegate {

    private static final Logger logger = LoggerFactory.getLogger(BaseRtPermisionsDelegate.class);

    //region Request codes
    private static final int RC_PERMISSION_AFTER_RATIONALE = 102;
    private static final int RC_PERMISSION = 103;
    //endregion
    //region SavedState
    private static final String SS_PERMISSION_DELEGATE_BUNDLE = "SS_PERMISSION_DELEGATE_BUNDLE";
    private static final String SS_REQUEST_IN_PROGRESS = "SS_REQUEST_IN_PROGRESS";
    private static final String SS_LIGHT_RATIONALE_SHOWN = "SS_LIGHT_RATIONALE_SHOWN";
    private static final String SS_HARD_RATIONALE_SHOWN = "SS_HARD_RATIONALE_SHOWN";
    //endregion

    private final Activity activity;
    private final ActivityNavigator activityNavigator;

    private Callback callback;
    private AlertDialog lightRationaleAlertDialog;
    private AlertDialog hardRationaleAlertDialog;

    private boolean requestInProgress;
    private boolean pausedForRequest;

    protected BaseRtPermisionsDelegate(Activity activity, ActivityNavigator activityNavigator) {
        this.activity = activity;
        this.activityNavigator = activityNavigator;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle bundle = savedInstanceState.getBundle(SS_PERMISSION_DELEGATE_BUNDLE);
            if (bundle != null) {
                requestInProgress = savedInstanceState.getBoolean(SS_REQUEST_IN_PROGRESS);
            }
        }
    }

    public void onResume() {
        if (pausedForRequest) {
            pausedForRequest = false;
        } else {
            checkPermissions();
        }
    }

    public void onPause() {
        pausedForRequest = requestInProgress;
    }

    public void onSaveInstanceState(Bundle outState) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SS_REQUEST_IN_PROGRESS, requestInProgress);
        bundle.putBoolean(SS_LIGHT_RATIONALE_SHOWN, lightRationaleAlertDialog != null);
        bundle.putBoolean(SS_HARD_RATIONALE_SHOWN, hardRationaleAlertDialog != null);
        outState.putBundle(SS_PERMISSION_DELEGATE_BUNDLE, bundle);
    }

    public void onDestroy() {
        dismissRationaleHardDialog();
        dismissRationaleLightDialog();
    }

    private void checkPermissions() {
        logger.trace("checkPermissions");
        if (requestInProgress) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(activity, getPermissionName()) == PackageManager.PERMISSION_GRANTED) {
                callback.onPermissionRequestFinished(true);
            } else if (activity.shouldShowRequestPermissionRationale(getPermissionName())) {
                showRationaleLightDialog();
            } else {
                requestPermissions(new String[]{getPermissionName()}, RC_PERMISSION);
            }
        } else {
            callback.onPermissionRequestFinished(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissions(@NonNull String[] permissions, int requestCode) {
        requestInProgress = true;
        activity.requestPermissions(permissions, requestCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ((requestCode == RC_PERMISSION || requestCode == RC_PERMISSION_AFTER_RATIONALE)) {
            requestInProgress = false;
            if (grantResults.length == 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callback.onPermissionRequestFinished(true);
                    return;
                }
                if (!activity.shouldShowRequestPermissionRationale(getPermissionName()) && requestCode == RC_PERMISSION) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        showRationaleHardDialog();
                        return;
                    }
                }
            }
            callback.onPermissionRequestFinished(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showRationaleLightDialog() {
        dismissRationaleHardDialog();
        if (lightRationaleAlertDialog != null) {
            return;
        }
        lightRationaleAlertDialog = new AlertDialog.Builder(activity)
                .setMessage(getRationaleLight())
                .setPositiveButton(R.string.common_rt_permissions_rationale_light_continue,
                        (dialog, which) -> requestPermissions(new String[]{getPermissionName()}, RC_PERMISSION_AFTER_RATIONALE))
                .setNegativeButton(R.string.common_rt_permissions_rationale_light_cancel,
                        (dialog, which) -> callback.onPermissionRequestFinished(false))
                .setOnDismissListener(dialog -> dismissRationaleLightDialog())
                .setCancelable(false)
                .show();
    }

    private void dismissRationaleLightDialog() {
        if (lightRationaleAlertDialog != null) {
            lightRationaleAlertDialog.dismiss();
            lightRationaleAlertDialog = null;
        }
    }

    private void showRationaleHardDialog() {
        dismissRationaleLightDialog();
        if (hardRationaleAlertDialog != null) {
            return;
        }
        hardRationaleAlertDialog = new AlertDialog.Builder(activity)
                .setMessage(getRationaleHard())
                .setPositiveButton(R.string.common_rt_permissions_rationale_hard_continue,
                        (dialog, which) -> activityNavigator.navigateToAppSettings(activity))
                .setNegativeButton(R.string.common_rt_permissions_rationale_hard_cancel,
                        (dialog, which) -> callback.onPermissionRequestFinished(false))
                .setOnDismissListener(dialog -> dismissRationaleHardDialog())
                .setCancelable(false)
                .show();
    }

    private void dismissRationaleHardDialog() {
        if (hardRationaleAlertDialog != null) {
            hardRationaleAlertDialog.dismiss();
            hardRationaleAlertDialog = null;
        }
    }

    /**
     * Возвращает наименование разрешения, с которым работает делегат.
     */
    protected abstract String getPermissionName();

    /**
     * Возвращает разъяснение о необходимости разрешения, когда
     * пользователь уже отказал без флага "Больше не спрашивать"
     */
    @StringRes
    protected abstract int getRationaleLight();

    /**
     * Возвращает разъяснение о необходимости разрешения, когда
     * пользователь уже отказал с флагом "Больше не спрашивать"
     */
    @StringRes
    protected abstract int getRationaleHard();

    public interface Callback {
        /**
         * Вызывается по заверешнию проверки разрешений
         *
         * @param granted {@code true} если рпзрешения были предоставлены, {@code false} иначе
         */
        void onPermissionRequestFinished(boolean granted);
    }

}
