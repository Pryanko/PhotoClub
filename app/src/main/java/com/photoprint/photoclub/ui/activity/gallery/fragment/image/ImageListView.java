package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import android.support.annotation.Nullable;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface ImageListView extends MvpView {
    void setCardParams(@Nullable Long id);
}
