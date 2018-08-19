package com.photoprint.photoclub.ui.activity.run;

import com.photoprint.photoclub.di.ScreenScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface RunScreenComponent {

    RunComponent.Builder runComponentBuilder();
}
