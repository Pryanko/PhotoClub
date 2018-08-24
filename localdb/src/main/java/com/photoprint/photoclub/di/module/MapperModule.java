package com.photoprint.photoclub.di.module;

import com.photoprint.photoclub.room.mapper.CategoryMapper;
import com.photoprint.photoclub.room.mapper.CategoryMapperImpl;
import com.photoprint.photoclub.room.mapper.GuideMapper;
import com.photoprint.photoclub.room.mapper.GuideMapperImpl;
import com.photoprint.photoclub.room.mapper.ServiceMapper;
import com.photoprint.photoclub.room.mapper.ServiceMapperImpl;
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

    @Provides
    @Reusable
    CategoryMapper categoryMapper() {
        return new CategoryMapperImpl();
    }

    @Provides
    @Reusable
    GuideMapper guideMapper() {
        return new GuideMapperImpl();
    }

    @Provides
    @Reusable
    ServiceMapper serviceMapper() {
        return new ServiceMapperImpl();
    }
}
