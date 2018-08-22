package com.photoprint.photoclub.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.utils.Ref;
import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.User;
import com.photoprint.photoclub.repository.UserRepository;
import com.photoprint.photoclub.room.dao.UserDao;
import com.photoprint.photoclub.room.entity.UserEntity;
import com.photoprint.photoclub.room.mapper.UserMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserEntity, Long> implements UserRepository {

    private final UserMapper mapper;

    @Inject
    UserRepositoryImpl(AppDatabase appDatabase,
                       UserMapper userMapper) {
        super(appDatabase);
        this.mapper = userMapper;
    }

    @Override
    protected UserMapper mapper() {
        return mapper;
    }

    @Override
    protected UserDao dao() {
        return appDatabase.userDao();
    }

    @Nullable
    @Override
    public User getCurrent() {
        return mapper().entityToModel(dao().getCurrent());
    }

    @NonNull
    @Override
    public Single<Ref<User>> getCurrentRx() {
        return dao().getCurrentRx()
                .map(userEntity -> {
                    if (userEntity == null) {
                        return Ref.<User>nullRef();
                    } else {
                        User user = mapper.entityToModel(userEntity);
                        return new Ref<>(user);
                    }
                });
    }

    @Override
    public void deleteAll() {
        dao().deleteAll();
    }
}
