package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.serviceinfo;

import com.photoprint.photoclub.di.FragmentScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@FragmentScope
@Subcomponent
public interface ServiceInfoFragmentComponent {

    void inject(ServiceInfoFragment serviceInfoFragment);

    ServiceInfoFragmentPresenter serviceInfoFragmentPresenter();
}
