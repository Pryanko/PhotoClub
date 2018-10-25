package com.photoprint.photoclub.ui.activity.gallery.fragment.image;

import com.photoprint.photoclub.ui.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
interface ImageListView extends MvpView {
    void setImageSettingCardVisible(boolean visible, int lastItemClicked);
}
