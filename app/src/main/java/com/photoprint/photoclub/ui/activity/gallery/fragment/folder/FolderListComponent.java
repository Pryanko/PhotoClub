package com.photoprint.photoclub.ui.activity.gallery.fragment.folder;

import com.photoprint.photoclub.di.FragmentScope;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@FragmentScope
@Subcomponent(modules = FolderListModule.class)
public interface FolderListComponent {

    void inject(FolderListFragment folderListFragment);

    FolderListPresenter folderListPresenter();
}
