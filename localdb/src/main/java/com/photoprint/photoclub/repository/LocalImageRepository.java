package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.LocalImage;
import com.photoprint.photoclub.repository.base.BaseRepository;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface LocalImageRepository extends BaseRepository<LocalImage, Long> {

    @NonNull
    List<LocalImage> getImagesByFolder(String folder);

    void deleteAll();

}
