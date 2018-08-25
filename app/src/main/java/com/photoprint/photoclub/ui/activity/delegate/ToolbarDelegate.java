package com.photoprint.photoclub.ui.activity.delegate;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.utils.TextUtils;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.BaseActivity;

import javax.inject.Inject;

/**
 * Делегат для обработки тулбара.
 *
 * @author Grigoriy Pryamov.
 */
@ActivityScope
public class ToolbarDelegate {

    private final BaseActivity activity;
    private Toolbar toolbar;
    private TextView customTitle;
    private TextView customSubtitle;

    @Inject
    ToolbarDelegate(@NonNull BaseActivity activity) {
        this.activity = activity;
    }

    public void init() {
        toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        customTitle = activity.findViewById(R.id.customTitle);
        customSubtitle = activity.findViewById(R.id.customSubtitle);
    }

    public void setTitle(@StringRes int resId) {
        activity.setTitle(resId);
        customTitle.setText(resId);
    }

    public void setTitle(CharSequence title) {
        activity.setTitle(title);
        customTitle.setText(title);
    }

    public void setSubtitle(@StringRes int resId) {
        customSubtitle.setText(resId);
    }

    public void setSubtitle(CharSequence title) {
        customSubtitle.setText(title);
        customSubtitle.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
    }

    public void setBackgroundColor(@ColorRes int resId) {
        toolbar.setBackgroundResource(resId);
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        toolbar.setNavigationIcon(resId);
    }

    public void setNavigationIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
    }

    public void setNavigationOnClickListener(View.OnClickListener listener) {
        toolbar.setNavigationOnClickListener(listener);
    }
}
