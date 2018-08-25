package com.photoprint.photoclub.ui.activity.guide;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface GuideComponent {

    void inject(GuideActivity guideActivity);

    GuidePresenter guidePresenter();

    @Subcomponent.Builder
    interface Builder {
        GuideComponent.Builder activityModule(ActivityModule activityModule);

        GuideComponent build();
    }
}
