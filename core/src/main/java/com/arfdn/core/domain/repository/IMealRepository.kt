package com.arfdn.core.domain.repository
import com.arfdn.core.data.Resource
import com.arfdn.core.domain.model.MealItem
import kotlinx.coroutines.flow.Flow

interface IMealRepository {

    fun getAllMeals(area: String): Flow<Resource<List<MealItem>>>
    fun getFavoriteMeals(): Flow<List<MealItem>>
    fun setFavoriteMeal(tourism: MealItem, state: Boolean)
}