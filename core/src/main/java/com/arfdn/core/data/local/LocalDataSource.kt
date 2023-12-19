package com.arfdn.core.data.local

import com.arfdn.core.data.local.entity.MealDetailEntity
import com.arfdn.core.data.local.entity.MealEntity
import com.arfdn.core.data.local.room.MealDao
import com.arfdn.core.domain.model.MealDetail
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealDao: MealDao) {

    fun getAllMeals(): Flow<List<MealEntity>> = mealDao.getAllMeals()

    fun getDetailMeal(idMeal: String): Flow<MealDetailEntity> = mealDao.getDetailMeal(idMeal)

    fun getFavoriteMeals(): Flow<List<MealEntity>> = mealDao.getFavoriteMeals()

    fun getFavoriteMealById(id: String): Flow<MealEntity>? = mealDao.getFavoriteMealById(id)

    suspend fun insertMeals(mealList: List<MealEntity>) = mealDao.insertMeals(mealList)
    suspend fun insertDetailMeal(meal: List<MealDetailEntity>) = mealDao.insertDetailMeal(meal)
    fun setFavoriteMeal(meal: MealEntity, newState: Boolean) {
        meal.isFavorite = newState
        mealDao.updateFavoriteMeal(meal)
    }

}