package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.FolderListComponent;

import dagger.Subcomponent;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface GalleryComponent {

    void inject(GalleryActivity galleryActivity);

    GalleryPresenter galleryPresenter();

    FolderListComponent folderComponent();

    @Subcomponent.Builder
    interface Builder {
        GalleryComponent.Builder activityModule(ActivityModule activityModule);

        GalleryComponent build();
    }
}
