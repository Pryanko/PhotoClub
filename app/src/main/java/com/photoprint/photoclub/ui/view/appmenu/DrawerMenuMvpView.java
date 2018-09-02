package com.photoprint.photoclub.ui.view.appmenu;

import android.support.annotation.Nullable;

import com.photoprint.photoclub.ui.mvp.view.MvpView;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuItem;

/**
 * @author Grigoriy Pryamov.
 */
interface DrawerMenuMvpView extends MvpView {

    void setSelectedItem(@Nullable DrawerMenuItem drawerMenuItem);

    void setVersionName(String name, int code);

    void setUserName(String userName);
}
