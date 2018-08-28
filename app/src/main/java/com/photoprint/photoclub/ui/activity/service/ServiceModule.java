package com.photoprint.photoclub.ui.activity.service;

import com.photoprint.photoclub.ui.activity.service.adapter.ServiceListAdapter;
import com.photoprint.photoclub.ui.activity.service.adapter.ServiceListAdapterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class ServiceModule {

    @Binds
    abstract ServiceListAdapter serviceListAdapter(ServiceListAdapterImpl serviceListAdapter);
}
