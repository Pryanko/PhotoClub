package com.photoprint.photoclub.di;

import com.photoprint.photoclub.AppComponent;

/**
 * @author Grigoriy Pryamov.
 */
public class Dagger {

    private static AppComponent appComponent;

    public static AppComponent appComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        Dagger.appComponent = appComponent;
    }
}
