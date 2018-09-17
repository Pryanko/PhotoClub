package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist;

import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter.MaquetteListAdapter;
import com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter.MaquetteListAdapterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Grigoriy Pryamov.
 */
@Module
public abstract class MaquetteListModule {

    @Binds
    abstract MaquetteListAdapter maquetteListAdapter(MaquetteListAdapterImpl maquetteListAdapter);
}
