package com.photoprint.photoclub.data.interactor;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.storage.StorageManager;
import com.photoprint.photoclub.model.LocalImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Поставщик фотографий устройства
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class LocalImagesProvider {

    private static final Logger logger = LoggerFactory.getLogger(LocalImagesProvider.class);

    private final static Integer DEFAULT_COUNT_PRINT_IMAGE = 1;
    private final static String DEFAULT_NAME_SYSTEM_PATH = "Pictures";

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
                String folder = getFolderImage(path);
                localImage.setParentFolder(folder);
                logger.trace(folder);
                localImage.setCountPrint(DEFAULT_COUNT_PRINT_IMAGE);
                localImages.add(localImage);
            }
            return localImages;
        });
    }

    @NonNull
    private String getFolderImage(String path) {
        List<String> folderList = Arrays.asList(path.split("/"));
        String folder = folderList.get(6);
        if (folder.equals(DEFAULT_NAME_SYSTEM_PATH)) {
            folder = folderList.get(7);
        }
        if (folder != null) {
            return folder;
        } else {
            return DEFAULT_NAME_SYSTEM_PATH;
        }
    }
}
