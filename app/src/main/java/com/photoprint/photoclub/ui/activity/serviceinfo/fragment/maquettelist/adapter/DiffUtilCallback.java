package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter;

import com.photoprint.photoclub.model.Maquette;
import com.photoprint.photoclub.ui.adapter.base.BaseDiffUtilCallback;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class DiffUtilCallback extends BaseDiffUtilCallback<Maquette> {

    public DiffUtilCallback(List<Maquette> oldItems, List<Maquette> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean areItemsTheSame(Maquette oldItem, Maquette newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Maquette oldItem, Maquette newItem) {
        return oldItem.equals(newItem);
    }
}
