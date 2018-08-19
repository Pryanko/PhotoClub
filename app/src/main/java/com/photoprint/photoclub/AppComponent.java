package com.photoprint.photoclub;

import com.photoprint.network.NetworkModule;
import com.photoprint.photoclub.ui.mvp.core.MvpProcessor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(AppInitProvider appInitProvider);

    MvpProcessor mvpProcessor();
}
