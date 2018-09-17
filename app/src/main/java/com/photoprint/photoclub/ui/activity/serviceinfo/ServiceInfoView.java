package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface ServiceInfoView extends MvpView {

    void setMaquetteFragmentVisible(boolean fragmentVisible);

    void showMaquetteList();

    void hideMaquetteList();

    void setMaquetteName(String name);
}
