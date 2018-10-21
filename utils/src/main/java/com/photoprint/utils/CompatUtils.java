package com.photoprint.utils;

import android.content.Context;

/**
 * @author Libgo on 02.04.2018.
 */
public class CompatUtils {

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
