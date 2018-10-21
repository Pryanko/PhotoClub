package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GalleryViewState extends BaseMvpViewState<GalleryView> implements GalleryView {

    private String folder;

    @Inject
    GalleryViewState() {
    }

    @Override
    protected void onViewAttached(GalleryView view) {
        if (folder == null) {
            view.hideImageList();
        } else {
            view.showImageList(folder);
        }
    }

    @Override
    protected void onViewDetached(GalleryView view) {

    }

    @Override
    public void showImageList(String nameFolder) {
        this.folder = nameFolder;
        forEachView(view -> view.showImageList(this.folder));
    }

    @Override
    public void hideImageList() {
        this.folder = null;
        forEachView(GalleryView::hideImageList);
    }
}
