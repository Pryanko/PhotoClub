package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.repository.base.BaseRepository;

import java.util.List;

/**
 * Репозиторий для гайдов обучения
 *
 * @author Grigoriy Pryamov.
 */
public interface GuideRepository extends BaseRepository<Guide, Long> {
    /**
     * Возврщащет список всех гайдов
     */
    @NonNull
    List<Guide> getGuides();

    /**
     * Метод удаляющий все категории
     */
    void deleteAll();
}
