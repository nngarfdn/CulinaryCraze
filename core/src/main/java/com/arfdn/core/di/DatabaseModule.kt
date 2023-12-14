package com.arfdn.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.arfdn.core.data.local.room.MealDao
import com.arfdn.core.data.local.room.MealDatabase

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MealDatabase = Room.databaseBuilder(
        context,
        MealDatabase::class.java, "Meal.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: MealDatabase): MealDao = database.mealDao()
}