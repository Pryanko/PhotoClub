package com.photoprint.photoclub.impl.base;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.model.base.ModelWithId;
import com.photoprint.photoclub.repository.base.BaseRepository;
import com.photoprint.photoclub.room.dao.base.BaseDao;
import com.photoprint.photoclub.room.entity.base.EntityWithId;
import com.photoprint.photoclub.room.mapper.base.BaseMapper;

import java.util.List;

/**
 * Базовый репозиторий
 *
 * @author Grigoriy Pryamov.
 */
public abstract class BaseRepositoryImpl<Model extends ModelWithId<Id>, Entity extends EntityWithId<Id>, Id>
        extends RepositoryImpl implements BaseRepository<Model, Id> {

    protected BaseRepositoryImpl(AppDatabase appDatabase) {
        super(appDatabase);
    }

    protected abstract BaseMapper<Model, Entity> mapper();

    protected abstract BaseDao<Entity> dao();

    @Override
    public void insert(@NonNull Model model) {
        Entity entity = mapper().modelToEntity(model);
        dao().insert(entity);
    }

    @Override
    public void insert(@NonNull List<Model> models) {
        List<Entity> entities = mapper().modelListToEntityList(models);
        dao().insert(entities);
    }

    @Override
    public void update(@NonNull Model model) {
        dao().update(mapper().modelToEntity(model));
    }

    @Override
    public void delete(@NonNull Model model) {
        dao().delete(mapper().modelToEntity(model));
    }
}
