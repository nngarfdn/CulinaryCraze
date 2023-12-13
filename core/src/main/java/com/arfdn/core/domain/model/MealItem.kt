package com.arfdn.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealItem(
    val idMeal: String = "",
    val strMeal: String = "",
    val strMealThumb: String = "",
    val isFavorite: Boolean = false
): Parcelable