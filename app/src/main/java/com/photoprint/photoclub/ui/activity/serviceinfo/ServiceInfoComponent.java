package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo.ServiceInfoFragmentComponent;
import com.photoprint.photoclub.ui.activity.serviceinfo.model.ServiceInfoParams;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ServiceInfoComponent {

    void inject(ServiceInfoActivity serviceInfoActivity);

    ServiceInfoPresenter serviceInfoPresenter();

    ServiceInfoFragmentComponent serviceInfoFragmentComponent();

    @Subcomponent.Builder
    interface Builder {
        ServiceInfoComponent.Builder activityModule(ActivityModule activityModule);

        @BindsInstance
        ServiceInfoComponent.Builder serviceInfoParams(ServiceInfoParams serviceInfoParams);

        ServiceInfoComponent build();
    }
}
