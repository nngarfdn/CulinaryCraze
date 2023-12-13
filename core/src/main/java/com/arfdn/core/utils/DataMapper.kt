package com.arfdn.core.utils

import com.arfdn.core.data.local.entity.MealEntity
import com.arfdn.core.data.remote.response.Meal
import com.arfdn.core.data.remote.response.MealsResponse
import com.arfdn.core.domain.model.MealItem

object DataMapper {
    fun mapResponsesToEntities(input: MealsResponse): List<MealEntity> {
        val mealList = ArrayList<MealEntity>()
        input.meals.forEach { mealsResponse ->
                val meal = MealEntity(
                    idMeal = mealsResponse.idMeal,
                    strMeal = mealsResponse.strMeal,
                    strMealThumb = mealsResponse.strMealThumb,
                    isFavorite = false
                )
                mealList.add(meal)
            }
        return mealList
    }

    fun mapEntitiesToDomain(input: List<MealEntity>): List<MealItem> =
        input.map {
            MealItem(
                idMeal = it.idMeal,
                strMeal = it.strMeal,
                strMealThumb = it.strMealThumb
            )
        }

    fun mapDomainToEntity(input: MealItem): MealEntity = MealEntity(
        idMeal = input.idMeal,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb,
        isFavorite = input.isFavorite
    )
}
