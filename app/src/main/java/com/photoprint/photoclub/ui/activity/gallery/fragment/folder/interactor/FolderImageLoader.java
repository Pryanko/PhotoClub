package com.photoprint.photoclub.ui.activity.gallery.fragment.folder.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.ui.activity.gallery.fragment.folder.model.Folder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик списка папок с фотографиями
 *
 * @author Grigoriy Pryamov.
 */
public class FolderImageLoader {

    private static final Logger logger = LoggerFactory.getLogger(FolderImageLoader.class);

    private final LocalImageRepository localImageRepository;

    @Inject
    public FolderImageLoader(LocalImageRepository localImageRepository) {
        this.localImageRepository = localImageRepository;
    }

    public Single<Result> getFolders() {
        logger.trace("getFolders");
        return Single.fromCallable(localImageRepository::getFolders)
                .map(this::mapToFolder)
                .map(folders -> new Result(UserError.NO_ERROR, folders))
//              .onErrorReturn(throwable -> {
//                    UserError userError = userErrorBuilder.fromThrowable(throwable);
//                    return new Result(userError, Collections.emptyList(), true);
//                })
                .subscribeOn(AppSchedulers.db());
    }

    private List<Folder> mapToFolder(List<LocalImage> localImages) {
        List<Folder> folders = new ArrayList<>();
        for (LocalImage localImage : localImages) {
            Folder folder = new Folder();
            folder.setName(localImage.getParentFolder());
            folder.setFolderImage(localImage.getFullPath());
            folders.add(folder);
        }
        return folders;
    }

    public static class Result {

        private final UserError userError;
        private final List<Folder> folders;

        Result(UserError userError, List<Folder> folders) {
            this.userError = userError;
            this.folders = folders;
        }

        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<Folder> getFolders() {
            return folders;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
