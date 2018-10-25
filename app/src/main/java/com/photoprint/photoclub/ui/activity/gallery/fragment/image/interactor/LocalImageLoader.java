package com.photoprint.photoclub.ui.activity.gallery.fragment.image.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.interactor.FolderImageLoader;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик фоток для галереи
 *
 * @author Grigoriy Pryamov.
 */
public class LocalImageLoader {

    private static final Logger logger = LoggerFactory.getLogger(FolderImageLoader.class);

    private final LocalImageRepository localImageRepository;

    @Inject
    public LocalImageLoader(LocalImageRepository localImageRepository) {
        this.localImageRepository = localImageRepository;
    }

    public Single<Result> getLocalImagesByFolder(String folder) {
        logger.trace("getLocalImagesByFolder");
        return Single.fromCallable(() -> localImageRepository.getImagesByFolder(folder))
                .map(localImages -> new Result(UserError.NO_ERROR, localImages))
//              .onErrorReturn(throwable -> {
//                    UserError userError = userErrorBuilder.fromThrowable(throwable);
//                    return new Result(userError, Collections.emptyList(), true);
//                })
                .subscribeOn(AppSchedulers.db());
    }

    public static class Result {

        private final UserError userError;
        private final List<LocalImage> localImages;

        Result(UserError userError, List<LocalImage> localImages) {
            this.userError = userError;
            this.localImages = localImages;
        }

        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<LocalImage> getLocalImages() {
            return localImages;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
