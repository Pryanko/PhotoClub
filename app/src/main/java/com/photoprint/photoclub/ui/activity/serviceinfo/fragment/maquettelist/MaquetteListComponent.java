package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist;

import com.photoprint.photoclub.di.FragmentScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@FragmentScope
@Subcomponent
public interface MaquetteListComponent {

    void inject(MaquetteListFragment maquetteListFragment);

    MaquetteListPresenter maquetteListPresenter();
}
