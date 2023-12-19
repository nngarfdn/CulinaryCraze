package com.arfdn.core.domain.usecase

import com.arfdn.core.data.Resource
import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.domain.model.MealDetail
import com.arfdn.core.domain.model.MealItem
import kotlinx.coroutines.flow.Flow

interface MealUseCase {
    fun getAllMeals(area: String): Flow<Resource<List<MealItem>>>
    fun getDetailMeal(idMeal: String): Flow<Resource<DetailMealResponse>>
    fun getFavoriteMeals(): Flow<List<MealItem>>

    fun getFavoriteMealById(idMeal: String): Flow<MealItem>?
    fun setFavoriteMeal(item: MealItem, state: Boolean)
}