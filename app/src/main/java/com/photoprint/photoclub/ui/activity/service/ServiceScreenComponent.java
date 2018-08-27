package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent(modules = {ServiceModule.class})
public interface ServiceScreenComponent {

    ServiceComponent.Builder serviceComponentBuilder();
}
