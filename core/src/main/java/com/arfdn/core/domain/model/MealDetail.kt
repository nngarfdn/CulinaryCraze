package com.arfdn.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealDetail(
    val dateModified: String? = String(),
    val idMeal: String = "",
    val strArea: String = "",
    val strCategory: String = "",
    val strCreativeCommonsConfirmed: String? = String(),
    val strDrinkAlternate: String? = String(),
    val strImageSource: String? = String(),
    val strIngredient: String = "",
    val strInstructions: String = "",
    val strMeal: String = "",
    val strMealThumb: String = "",
    val strMeasure: String = "",
    val strSource: String = "",
    val strTags: String = "",
    val strYoutube: String = "",
    val isFavorite: Boolean = false
) : Parcelable