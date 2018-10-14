package com.photoprint.photoclub.data.interactor;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.ImageInfo;
import com.photoprint.photoclub.data.storage.StorageManager;
import com.photoprint.photoclub.model.LocalImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Поставщик фотографий устройства
 *
 * @author Grigoriy Pryamov.
 */
public class LocalImagesProvider {

    private static final Logger logger = LoggerFactory.getLogger(LocalImagesProvider.class);

    private final static Integer DEFAULT_COUNT_PRINT_IMAGE = 1;
    private final static String DEFAULT_NAME_SYSTEM_PATH = "Pictures";

    private final StorageManager storageManager;

    @Inject
    LocalImagesProvider(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public Single<List<LocalImage>> getLocalImagesRx() {
        return Single.fromCallable(() -> {
            List<LocalImage> localImages = new ArrayList<>();
            List<String> imagePaths = storageManager.getLocalImagePaths();
            for (String path : imagePaths) {
                LocalImage localImage = new LocalImage();
                localImage.setFullPath(path);
                ImageInfo imageInfo = getImageInfo(path);
                String folder = imageInfo.getFolder();
                String name = imageInfo.getName();
                localImage.setParentFolder(folder);
                localImage.setName(name);
                logger.trace(folder);
                logger.trace(name);
                localImage.setCountPrint(DEFAULT_COUNT_PRINT_IMAGE);
                localImages.add(localImage);
            }
            return localImages;
        });
    }

    /**
     * Метод возращает папку и имя файла
     *
     * @param path путь к фотографии
     */
    @NonNull
    ImageInfo getImageInfo(String path) {
        ImageInfo imageInfo = new ImageInfo();
        List<String> folderList = Arrays.asList(path.split("/"));
        int sizeList = folderList.size();
        String folder;
        if (sizeList >= 6) {
            folder = folderList.get(6);
        } else {
            folder = DEFAULT_NAME_SYSTEM_PATH;
        }
        if (folder.equals(DEFAULT_NAME_SYSTEM_PATH)) {
            if (sizeList >= 7) {
                folder = folderList.get(7);
            }
        }
        if (checkForPoint(folder)) {
            folder = DEFAULT_NAME_SYSTEM_PATH;
        }
        imageInfo.setFolder(folder.toUpperCase());
        imageInfo.setName(folderList.get(sizeList - 1));
        return imageInfo;
    }

    /**
     * Метод проверяющий, что в папке нет точки, что бы исключить подмену имени папки на имя файла
     */
    private boolean checkForPoint(@NonNull String folder) {
        return folder.contains(".");
    }
}
