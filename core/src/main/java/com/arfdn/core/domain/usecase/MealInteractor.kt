package com.arfdn.core.domain.usecase

import com.arfdn.core.data.Resource
import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.domain.model.MealDetail
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.domain.repository.IMealRepository
import kotlinx.coroutines.flow.Flow

class MealInteractor(
    private val mealRepository: IMealRepository
): MealUseCase {
    override fun getAllMeals(area: String): Flow<Resource<List<MealItem>>> {
        return mealRepository.getAllMeals(area)
    }

    override fun getDetailMeal(idMeal: String): Flow<Resource<DetailMealResponse>> {
        return mealRepository.getDetailMeal(idMeal)
    }

    override fun getFavoriteMeals(): Flow<List<MealItem>> {
        return mealRepository.getFavoriteMeals()
    }

    override fun getFavoriteMealById(idMeal: String): Flow<MealItem>? {
        return mealRepository.getFavoriteMealById(idMeal)
    }

    override fun setFavoriteMeal(tourism: MealItem, state: Boolean) {
        return mealRepository.setFavoriteMeal(tourism, state)
    }
}