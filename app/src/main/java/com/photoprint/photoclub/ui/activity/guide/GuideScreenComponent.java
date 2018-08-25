package com.photoprint.photoclub.ui.activity.guide;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent(modules = GuideModule.class)
public interface GuideScreenComponent {

    GuideComponent.Builder guideComponentBuilder();
}
