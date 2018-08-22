package com.photoprint.photoclub.repository;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.repository.base.BaseRepository;

import java.util.List;

/**
 * Репозиторий для категорий
 *
 * @author Grigoriy Pryamov.
 */
public interface CategoryRepository extends BaseRepository<Category, Long> {
    /**
     * Возврщащет список всех категорий
     */
    @NonNull
    List<Category> getCategories();

    /**
     * Метод удаляющий все категории
     */
    void deleteAll();
}
