package com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.model.Folder;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface FolderListAdapter {
    void setFolders(@NonNull List<Folder> folders);
}
