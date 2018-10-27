package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
class ImageListViewState extends BaseMvpViewState<ImageListView> implements ImageListView {

    private Long imageId;

    @Inject
    ImageListViewState() {
    }

    @Override
    protected void onViewAttached(ImageListView view) {
        view.setCardParams(imageId);
    }

    @Override
    protected void onViewDetached(ImageListView view) {

    }

    @Override
    public void setCardParams(Long id) {
        this.imageId = id;
        forEachView(view -> view.setCardParams(this.imageId));
    }
}
