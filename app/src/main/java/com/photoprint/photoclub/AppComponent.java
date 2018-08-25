package com.photoprint.photoclub;

import com.photoprint.network.NetworkModule;
import com.photoprint.photoclub.di.module.LocalDatabaseModule;
import com.photoprint.photoclub.ui.activity.category.CategoryScreenComponent;
import com.photoprint.photoclub.ui.activity.run.RunScreenComponent;
import com.photoprint.photoclub.ui.mvp.core.MvpProcessor;

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

    RunScreenComponent runScreenComponent();

    CategoryScreenComponent categoryScreenComponent();
}
