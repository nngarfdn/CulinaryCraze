package com.arfdn.core.di

import com.arfdn.core.data.MealRepository
import com.arfdn.core.domain.repository.IMealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: MealRepository): IMealRepository

}