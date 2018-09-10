package com.photoprint.photoclub;

import com.photoprint.network.NetworkModule;
import com.photoprint.photoclub.di.module.LocalDatabaseModule;
import com.photoprint.photoclub.ui.activity.category.CategoryScreenComponent;
import com.photoprint.photoclub.ui.activity.guide.GuideScreenComponent;
import com.photoprint.photoclub.ui.activity.run.RunScreenComponent;
import com.photoprint.photoclub.ui.activity.service.ServiceScreenComponent;
import com.photoprint.photoclub.ui.activity.serviceinfo.ServiceInfoScreenComponent;
import com.photoprint.photoclub.ui.activity.servicesettings.ServiceSettingsScreenComponent;
import com.photoprint.photoclub.ui.mvp.core.MvpProcessor;
import com.photoprint.photoclub.ui.view.appmenu.DrawerMenuComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Di компонент приложения
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, LocalDatabaseModule.class})
public interface AppComponent {

    void inject(AppInitProvider appInitProvider);

    MvpProcessor mvpProcessor();

    DrawerMenuComponent drawerMenuComponent();

    RunScreenComponent runScreenComponent();

    CategoryScreenComponent categoryScreenComponent();

    GuideScreenComponent guideScreenComponent();

    ServiceScreenComponent serviceScreenComponent();

    ServiceSettingsScreenComponent ServiceSettingsScreenComponent();

    ServiceInfoScreenComponent ServiceInfoScreenComponent();
}
