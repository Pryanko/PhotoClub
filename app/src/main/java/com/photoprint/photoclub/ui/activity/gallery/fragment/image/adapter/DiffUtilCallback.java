package com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter;

import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.ui.adapter.base.BaseDiffUtilCallback;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class DiffUtilCallback extends BaseDiffUtilCallback<LocalImage> {

    public DiffUtilCallback(List<LocalImage> oldItems, List<LocalImage> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean areItemsTheSame(LocalImage oldItem, LocalImage newItem) {
        return oldItem.getId().equals(oldItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(LocalImage oldItem, LocalImage newItem) {
        return oldItem.equals(newItem);
    }
}
