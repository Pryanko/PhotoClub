package com.photoprint.photoclub.ui.activity.service.adapter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Service;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface ServiceListAdapter {
    void setServices(@NonNull List<Service> services);
}
