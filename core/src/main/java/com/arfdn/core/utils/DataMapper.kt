package com.arfdn.core.utils

import android.util.Log
import com.arfdn.core.data.local.entity.MealDetailEntity
import com.arfdn.core.data.local.entity.MealEntity
import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.data.remote.response.Meal
import com.arfdn.core.data.remote.response.MealsResponse
import com.arfdn.core.data.remote.response.getIngredient
import com.arfdn.core.data.remote.response.getMeasure
import com.arfdn.core.domain.model.MealDetail
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

    fun mapDetailResponseToEntities(input: DetailMealResponse): List<MealDetailEntity> {
        Log.d("mapDetailResponseToEntities", "mapDetailResponseToEntities: called")
        return listOf(MealDetailEntity(
            dateModified = input.meals[0].dateModified,
            idMeal = input.meals[0].idMeal,
            strArea = input.meals[0].strArea,
            strCategory = input.meals[0].strCategory,
            strCreativeCommonsConfirmed = input.meals[0].strCreativeCommonsConfirmed,
            strDrinkAlternate = input.meals[0].strDrinkAlternate,
            strImageSource = input.meals[0].strImageSource,
            strIngredient = input.meals[0].getIngredient(0),
            strInstructions = input.meals[0].strInstructions,
            strMeal = input.meals[0].strMeal,
            strMealThumb = input.meals[0].strMealThumb,
            strMeasure = input.meals[0].getMeasure(0),
            strSource = input.meals[0].strSource,
            strTags = input.meals[0].strTags,
            strYoutube = input.meals[0].strYoutube,
        ))

    }

    fun mapEntitiesToDomain(input: List<MealEntity>): List<MealItem> =
        input.map {
            MealItem(
                idMeal = it.idMeal,
                strMeal = it.strMeal,
                strMealThumb = it.strMealThumb
            )
        }

    fun mapEntityToDomain(input: MealEntity?): MealItem =
        MealItem(
            idMeal = input?.idMeal ?: "",
            strMeal = input?.strMeal ?: "",
            strMealThumb = input?.strMealThumb ?: "",
            isFavorite = input?.isFavorite ?: false
        )

    fun mapDetailEntitiesToDomain(input: MealDetailEntity): MealDetail =
        MealDetail(
            dateModified = input.dateModified,
            idMeal = input.idMeal,
            strArea = input.strArea,
            strCategory = input.strCategory,
            strCreativeCommonsConfirmed = input.strCreativeCommonsConfirmed,
            strDrinkAlternate = input.strDrinkAlternate,
            strImageSource = input.strImageSource,
            strIngredient = input.strIngredient,
            strInstructions = input.strInstructions,
            strMeal = input.strMeal,
            strMealThumb = input.strMealThumb,
            strMeasure = input.strMeasure,
            strSource = input.strSource,
            strTags = input.strTags,
            strYoutube = input.strYoutube,
            isFavorite = input.isFavorite
        )

    fun mapDomainToEntity(input: MealItem): MealEntity = MealEntity(
        idMeal = input.idMeal,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb,
        isFavorite = input.isFavorite
    )

    fun mapDetailDomainToEntity(input: MealDetail): MealDetailEntity = MealDetailEntity(
        dateModified = input.dateModified,
        idMeal = input.idMeal,
        strArea = input.strArea,
        strCategory = input.strCategory,
        strCreativeCommonsConfirmed = input.strCreativeCommonsConfirmed,
        strDrinkAlternate = input.strDrinkAlternate,
        strImageSource = input.strImageSource,
        strIngredient = input.strIngredient,
        strInstructions = input.strInstructions,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb,
        strMeasure = input.strMeasure,
        strSource = input.strSource,
        strTags = input.strTags,
        strYoutube = input.strYoutube,
        isFavorite = input.isFavorite
    )

    fun mapDetailMealEntityToMealEntity(input: MealDetail): MealItem = MealItem(
        idMeal = input.idMeal,
        strMeal = input.strMeal,
        strMealThumb = input.strMealThumb,
        isFavorite = input.isFavorite
    )

    fun mapMealDetailResponseToMealItem(input: DetailMealResponse): MealItem = MealItem(
        idMeal = input.meals[0].idMeal,
        strMeal = input.meals[0].strMeal,
        strMealThumb = input.meals[0].strMealThumb,
        isFavorite = false
    )
}
