package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GalleryViewState extends BaseMvpViewState<GalleryView> implements GalleryView {

    private boolean imageListVisible;
    private String folder;

    @Inject
    GalleryViewState() {
    }

    @Override
    protected void onViewAttached(GalleryView view) {
        view.setImageListVisible(imageListVisible);
    }

    @Override
    protected void onViewDetached(GalleryView view) {

    }

    @Override
    public void setImageListVisible(boolean imageListVisible) {
        this.imageListVisible = imageListVisible;
        forEachView(view -> view.setImageListVisible(imageListVisible));
    }

    @Override
    public void showImageList(String nameFolder) {
        this.folder = nameFolder;
        forEachView(view -> view.showImageList(this.folder));
    }
}
