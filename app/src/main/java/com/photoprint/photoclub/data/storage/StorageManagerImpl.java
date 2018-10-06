package com.photoprint.photoclub.data.storage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


/**
 * @author Grigoriy Pryamov.
 */
public class StorageManagerImpl implements StorageManager {

    private static final Logger logger = LoggerFactory.getLogger(StorageManager.class);

    private static final String PREFIX_IMAGE_PATH = "file://";

    private final ContentResolver contentResolver;

    @Inject
    public StorageManagerImpl(Context context) {
        this.contentResolver = context.getContentResolver();
    }

    @Override
    public List<String> getLocalImagePaths() {
        return readStorage();
    }

    private List<String> readStorage() {

        List<String> imagePaths = new ArrayList<>();

        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        Cursor imageCursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");

        if (imageCursor != null) {
            for (int i = 0; i < imageCursor.getCount(); i++) {
                int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String path = PREFIX_IMAGE_PATH + imageCursor.getString(dataColumnIndex);
                imagePaths.add(path);
                logger.trace(path);
            }
        } else {
            return Collections.emptyList();
        }
        imageCursor.close();
        return imagePaths;
    }
}
