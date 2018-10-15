package com.photoprint.photoclub.ui.activity.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.ui.activity.base.ActivityModule;
import com.photoprint.photoclub.ui.activity.base.MvpActivity;
import com.photoprint.photoclub.ui.activity.category.CategoryActivity;

import javax.inject.Inject;

/**
 * Галлерея
 *
 * @author Grigoriy Pryamov.
 */
public class GalleryActivity extends MvpActivity implements GalleryView {

    private static final Logger logger = LoggerFactory.getLogger(GalleryActivity.class);

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CategoryActivity.class);
    }

    //region di
    GalleryScreenComponent screenComponent;
    GalleryComponent component;
    @Inject
    Navigator navigator;

    private GalleryPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenComponent = getScreenComponent();
        component = screenComponent.galleryComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        presenter = getMvpDelegate().getPresenter(component::galleryPresenter, GalleryPresenter.class);

    }

    @Override
    protected void onResume() {
        navigator.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        navigator.onPause();
        super.onPause();
    }

    private GalleryScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().galleryScreenComponent();
        } else {
            return (GalleryScreenComponent) saved;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }
}
