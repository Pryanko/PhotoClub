package com.photoprint.photoclub.ui.activity.servicesettings;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
interface ServiceSettingsView extends MvpView {

    void setImage(String image);

    void setServiceName(String serviceName);

    void setServicePrice(String price);

    void setServiceNameList(@NonNull List<String> serviceNameList);

    void setServiceTypeList(@NonNull List<String> serviceTypeList);

    void showLoading(boolean loading);
}
