package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface ServiceInfoFragmentView extends MvpView {
    void setServiceImage(String image);

    void setServiceDescription(String description);

    void selectMaquetteErrorEnabled(boolean error);
}
