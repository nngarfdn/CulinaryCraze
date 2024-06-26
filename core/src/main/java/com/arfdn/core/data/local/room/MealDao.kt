package com.arfdn.core.data.local.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arfdn.core.data.local.entity.MealDetailEntity
import com.arfdn.core.data.local.entity.MealEntity
import com.arfdn.core.data.remote.response.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meal")
    fun getAllMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM meal_detail WHERE idMeal = :idMeal")
    fun getDetailMeal(idMeal: String): Flow<MealDetailEntity>

    @Query("SELECT * FROM meal where isFavorite = 1")
    fun getFavoriteMeals(): Flow<List<MealEntity>>
    @Query("SELECT * FROM meal WHERE idMeal = :id AND isFavorite = 1")
    fun getFavoriteMealById(id: String): Flow<MealEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMeal(meal: List<MealDetailEntity>)

    @Update
    fun updateFavoriteMeal(meal: MealEntity)
}
