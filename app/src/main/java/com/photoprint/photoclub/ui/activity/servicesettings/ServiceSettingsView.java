package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface ServiceSettingsView extends MvpView {

    void setImage(String image);

    void setServiceName(String serviceName);

    void setServicePrice(String price);
}
