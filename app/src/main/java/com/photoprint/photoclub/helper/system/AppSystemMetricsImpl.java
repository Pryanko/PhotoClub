package com.photoprint.photoclub.helper.system;

import java.util.Date;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * @author Grigoriy Pryamov.
 */
@Reusable
public class AppSystemMetricsImpl implements AppSystemMetrics {

    @Inject
    AppSystemMetricsImpl() {
    }

    @Override
    public long getCurrentDeviceTime() {
        return System.currentTimeMillis();
    }

    @Override
    public Date getCurrentDeviceDate() {
        return new Date();
    }
}
