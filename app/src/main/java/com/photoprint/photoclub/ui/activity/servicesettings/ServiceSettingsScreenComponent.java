package com.photoprint.photoclub.ui.activity.servicesettings;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface ServiceSettingsScreenComponent {

    ServiceSettingsComponent.Builder serviceSettingsComponentBuilder();
}
