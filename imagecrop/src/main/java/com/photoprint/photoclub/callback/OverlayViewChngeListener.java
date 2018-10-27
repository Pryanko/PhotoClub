package com.photoprint.photoclub.callback;

import android.graphics.RectF;

/**
 * @author Grigoriy Pryamov.
 */
public interface OverlayViewChngeListener {

    void onCropRectUpdated(RectF cropRect);
}
