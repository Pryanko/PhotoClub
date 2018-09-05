package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.servicesettings.model.ServiceSettingsParams;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ServiceSettingsComponent {

    void inject(ServiceSettingsActivity serviceSettingsActivity);

    ServiceSettingsPresenter serviceSettingsPresenter();

    @Subcomponent.Builder
    interface Builder {
        ServiceSettingsComponent.Builder activityModule(ActivityModule activityModule);

        @BindsInstance
        ServiceSettingsComponent.Builder serviceSettingsParams(ServiceSettingsParams serviceSettingsParams);

        ServiceSettingsComponent build();
    }
}
