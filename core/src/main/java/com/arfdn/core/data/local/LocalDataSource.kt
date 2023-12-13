package com.arfdn.core.data.local

import com.arfdn.core.data.local.entity.MealEntity
import com.arfdn.core.data.local.room.MealDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao: MealDao) {

    fun getAllMeals(): Flow<List<MealEntity>> = mealDao.getAllMeals()

    fun getFavoriteMeals(): Flow<List<MealEntity>> = mealDao.getFavoriteMeals()

    suspend fun insertMeals(mealList: List<MealEntity>) = mealDao.insertMeals(mealList)

    fun setFavoriteMeal(meal: MealEntity, newState: Boolean) {
        meal.isFavorite = newState
        mealDao.updateFavoriteMeal(meal)
    }

}