package com.photoprint.photoclub.di.module;

import com.photoprint.photoclub.impl.UserRepositoryImpl;
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
}
