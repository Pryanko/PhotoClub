package com.photoprint.photoclub.ui.activity.delegate;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.utils.TextUtils;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ActivityScope;
import com.photoprint.photoclub.ui.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Делегат для обработки тулбара.
 *
 * @author Grigoriy Pryamov.
 */
@ActivityScope
public class ToolbarDelegate {

    private static final Logger logger = LoggerFactory.getLogger(ToolbarDelegate.class);

    private final BaseActivity activity;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.customTitle)
    TextView customTitle;
    @BindView(R.id.customSubtitle)
    TextView customSubtitle;
    @BindView(R.id.basketButton)
    ImageButton basketButton;
    @BindView(R.id.basketCount)
    TextView basketCount;

    @Inject
    ToolbarDelegate(@NonNull BaseActivity activity) {
        this.activity = activity;
    }

    public void init() {
        ButterKnife.bind(this, activity);
        activity.setSupportActionBar(toolbar);
        setBasketCount(15);
        basketButton.setOnClickListener(v -> logger.trace("basketButton click"));
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

    public void setBasketCount(int count) {
        basketCount.setText(String.valueOf(count));
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
