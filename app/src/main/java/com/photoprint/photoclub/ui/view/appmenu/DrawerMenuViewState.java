package com.photoprint.photoclub.ui.view.appmenu;

import android.support.annotation.Nullable;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuItem;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class DrawerMenuViewState extends BaseMvpViewState<DrawerMenuMvpView> implements DrawerMenuMvpView {

    private DrawerMenuItem selectedItem = null;

    @Inject
    DrawerMenuViewState() {
    }

    @Override
    protected void onViewAttached(DrawerMenuMvpView view) {
        view.setSelectedItem(selectedItem);
    }

    @Override
    protected void onViewDetached(DrawerMenuMvpView view) {

    }

    @Override
    public void setSelectedItem(@Nullable DrawerMenuItem drawerMenuItem) {
        this.selectedItem = drawerMenuItem;
        forEachView(view -> view.setSelectedItem(selectedItem));
    }

    @Override
    public void setVersionName(String name, int code) {

    }

    @Override
    public void setUserName(String userName) {

    }
}
