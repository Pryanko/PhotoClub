package com.photoprint.photoclub.ui.activity.gallery.fragment.image.adapter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.LocalImage;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface ImageListAdapter {

    void clearAdapter();

    void setImageList(@NonNull List<LocalImage> localImages);

    void setImageSelected(int position);
}
