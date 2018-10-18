package com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter;

import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.model.Folder;
import com.photoprint.photoclub.ui.adapter.base.BaseDiffUtilCallback;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class DiffUtilCallback extends BaseDiffUtilCallback<Folder> {

    public DiffUtilCallback(List<Folder> oldItems, List<Folder> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean areItemsTheSame(Folder oldItem, Folder newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    protected boolean areContentsTheSame(Folder oldItem, Folder newItem) {
        return oldItem.equals(newItem);
    }
}
