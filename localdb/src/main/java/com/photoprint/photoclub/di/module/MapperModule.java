package com.photoprint.photoclub.di.module;

import com.photoprint.photoclub.room.mapper.UserMapper;
import com.photoprint.photoclub.room.mapper.UserMapperImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Маппер модуль
 *
 * @author Grigoriy Pryamov.
 */
@Module
public class MapperModule {
    @Provides
    @Reusable
    UserMapper userMapper() {
        return new UserMapperImpl();
    }
}
