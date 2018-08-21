package com.photoprint.photoclub.repository.base;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.base.ModelWithId;

import java.util.List;

/**
 * Репозиторий для классической сущности локальной БД.
 *
 * @author Grigoriy Pryamov.
 */
public interface BaseRepository<Model extends ModelWithId<Id>, Id> extends Repository {

    void insert(@NonNull Model model);

    void insert(@NonNull List<Model> models);

    void update(@NonNull Model model);

    void delete(@NonNull Model model);
}
