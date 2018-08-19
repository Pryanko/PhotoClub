package com.photoprint.photoclub.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.AppComponent;
import com.photoprint.photoclub.di.Dagger;

/**
 * @author Grigoriy Pryamov.
 */
public class BaseActivity extends AppCompatActivity {

    private final Logger logger = LoggerFactory.getLogger(BaseActivity.class);

    protected final AppComponent appComponent() {
        return Dagger.appComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        logger.trace("onCreate: " + this);
        getWindow().setBackgroundDrawableResource(getBackgroundDrawableResource());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logger.trace("onNewIntent: " + this);
    }

    @Override
    protected void onStart() {
        logger.trace("onStart: " + this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        logger.trace("onResume: " + this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        logger.trace("onPause: " + this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        logger.trace("onStop: " + this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        logger.trace("onDestroy: " + this);
        super.onDestroy();
    }

    @DrawableRes
    protected int getBackgroundDrawableResource() {
        return android.R.color.white;
    }
}
