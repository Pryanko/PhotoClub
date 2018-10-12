package com.photoprint.photoclub.di.module;

import com.photoprint.photoclub.impl.CategoryRepositoryImpl;
import com.photoprint.photoclub.impl.GuideRepositoryImpl;
import com.photoprint.photoclub.impl.LocalImageRepositoryImpl;
import com.photoprint.photoclub.impl.ServiceRepositoryImpl;
import com.photoprint.photoclub.impl.UserRepositoryImpl;
import com.photoprint.photoclub.repository.CategoryRepository;
import com.photoprint.photoclub.repository.GuideRepository;
import com.photoprint.photoclub.repository.LocalImageRepository;
import com.photoprint.photoclub.repository.ServiceRepository;
import com.photoprint.photoclub.repository.UserRepository;

import dagger.Binds;
import dagger.Module;

/**
 * Di модуль репозиториев локальной БД
 *
 * @author Grigoriy Pryamov.
 */
@Module
public abstract class RepositoryModule {
    @Binds
    abstract UserRepository userRepository(UserRepositoryImpl userRepository);

    @Binds
    abstract CategoryRepository categoryRepository(CategoryRepositoryImpl categoryRepository);

    @Binds
    abstract GuideRepository guideRepository(GuideRepositoryImpl guideRepository);

    @Binds
    abstract ServiceRepository serviceRepository(ServiceRepositoryImpl serviceRepository);

    @Binds
    abstract LocalImageRepository localImageRepository(LocalImageRepositoryImpl localImageRepository);
}
