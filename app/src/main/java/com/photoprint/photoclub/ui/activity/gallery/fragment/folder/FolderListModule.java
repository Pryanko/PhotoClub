package com.photoprint.photoclub.ui.activity.gallery.fragment.folder;

import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter.FolderListAdapter;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.adapter.FolderListAdapterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class FolderListModule {

    @Binds
    abstract FolderListAdapter folderListAdapter(FolderListAdapterImpl folderListAdapter);
}
