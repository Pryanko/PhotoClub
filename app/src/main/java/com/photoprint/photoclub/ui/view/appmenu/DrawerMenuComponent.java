package com.photoprint.photoclub.ui.view.appmenu;

import com.photoprint.photoclub.di.CustomViewScope;
import com.photoprint.photoclub.ui.mvp.core.MvpProcessor;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@CustomViewScope
@Subcomponent
public interface DrawerMenuComponent {

    void inject(DrawerMenuView drawerMenuView);

    MvpProcessor mvpProcessor();

    DrawerMenuPresenter drawerMenuPresenter();
}
