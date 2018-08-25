package com.photoprint.photoclub;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.di.Dagger;
import com.photoprint.photoclub.helper.runtimepermission.RxErrorHandler;
import com.photoprint.photoclub.tools.SQLiteConnectionService;

import javax.inject.Inject;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * Инициаализация приложения
 *
 * @author Grigoriy Pryamov.
 */
public class AppInitProvider extends ContentProvider {

    private static final Logger logger = LoggerFactory.getLogger(AppInitProvider.class);

    //region DI
    @Inject
    SQLiteConnectionService sqLiteConnectionService;
    //endregion

    @Override
    public boolean onCreate() {
        logger.trace("onCreate");
        initDi(getContext());
        initRxJavaPlugins();
        initExternalSQLiteTool();
        frescoInit(getContext());
        return false;
    }

    private void initDi(Context appContext) {
        logger.trace("initDi");
        App app = (App) appContext;
        Dagger.setAppComponent(DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build());
        Dagger.appComponent().inject(this);
    }

    private void initRxJavaPlugins() {
        logger.trace("initRxJavaPlugins");
        RxJavaPlugins.setErrorHandler(new RxErrorHandler());
    }

    private void frescoInit(Context context) {
        Fresco.initialize(context);
    }

    private void initExternalSQLiteTool() {
        sqLiteConnectionService.start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }
}
