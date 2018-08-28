package com.photoprint.photoclub.ui.activity.service.adapter;

import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.ui.adapter.base.BaseDiffUtilCallback;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class DiffUtilCallback extends BaseDiffUtilCallback<Service> {

    public DiffUtilCallback(List<Service> oldItems, List<Service> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean areItemsTheSame(Service oldItem, Service newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Service oldItem, Service newItem) {
        return oldItem.equals(newItem);
    }
}
