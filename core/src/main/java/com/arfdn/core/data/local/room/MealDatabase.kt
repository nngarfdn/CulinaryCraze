package com.arfdn.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arfdn.core.data.local.entity.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase(){
    abstract fun mealDao(): MealDao
}