package com.photoprint.photoclub.ui.activity.serviceinfo;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface ServiceInfoScreenComponent {

    ServiceInfoComponent.Builder serviceInfoComponentBuilder();

}
