package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.service.model.ServiceParams;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ServiceComponent {

    void inject(ServiceActivity serviceActivity);

    ServicePresenter servicePresenter();

    @Subcomponent.Builder
    interface Builder {
        ServiceComponent.Builder activityModule(ActivityModule activityModule);

        @BindsInstance
        ServiceComponent.Builder serviceParams(ServiceParams serviceParams);

        ServiceComponent build();
    }
}
