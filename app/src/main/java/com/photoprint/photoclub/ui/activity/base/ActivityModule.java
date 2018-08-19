package com.photoprint.photoclub.ui.activity.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Grigoriy Pryamov.
 */
@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity activity() {
        return activity;
    }

    @Provides
    public AppCompatActivity appCompatActivity() {
        return activity;
    }

    @Provides
    public BaseActivity baseActivity() {
        return activity;
    }
}
