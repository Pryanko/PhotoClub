package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface RunComponent {

    void inject(RunActivity runActivity);

    RunPresenter runPresenter();

    @Subcomponent.Builder
    interface Builder {
        RunComponent.Builder activityModule(ActivityModule activityModule);

        RunComponent build();
    }
}
