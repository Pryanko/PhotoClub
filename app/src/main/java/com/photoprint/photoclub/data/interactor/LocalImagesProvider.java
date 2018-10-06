package com.photoprint.photoclub.data.interactor;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.storage.StorageManager;
import com.photoprint.photoclub.model.LocalImage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Поставщик фотографий устройства
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class LocalImagesProvider {

    private static final Logger logger = LoggerFactory.getLogger(LocalImagesProvider.class);

    private final static Integer DEFAULT_COUNT_PRINT_IMAGE = 1;

    private final StorageManager storageManager;

    @Inject
    public LocalImagesProvider(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public Single<List<LocalImage>> getLocalImagesRx() {
        return Single.fromCallable(() -> {
            List<LocalImage> localImages = new ArrayList<>();
            List<String> imagePaths = storageManager.getLocalImagePaths();
            for (String path : imagePaths) {
                LocalImage localImage = new LocalImage();
                localImage.setFullPath(path);
                localImage.setCountPrint(DEFAULT_COUNT_PRINT_IMAGE);
                localImages.add(localImage);
            }
            return localImages;
        });
    }
}
