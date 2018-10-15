package com.photoprint.photoclub.ui.activity.gallery;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
public class GalleryViewState extends BaseMvpViewState<GalleryView> implements GalleryView {

    @Inject
    GalleryViewState() {
    }

    @Override
    protected void onViewAttached(GalleryView view) {

    }

    @Override
    protected void onViewDetached(GalleryView view) {

    }
}
