package com.arfdn.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull

@Entity(tableName = "meal_detail")
data class MealDetailEntity(
    @ColumnInfo(name = "dateModified")
    val dateModified: String? = String(),

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMeal")
    val idMeal: String = "",

    @ColumnInfo(name = "strArea")
    val strArea: String = "",

    @ColumnInfo(name = "strCategory")
    val strCategory: String = "",

    @ColumnInfo(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? = String(),

    @ColumnInfo(name = "strDrinkAlternate")
    val strDrinkAlternate: String? = String(),

    @ColumnInfo(name = "strImageSource")
    val strImageSource: String? = String(),

    @ColumnInfo(name = "strIngredient")
    val strIngredient: String = "",

    @ColumnInfo(name = "strInstructions")
    val strInstructions: String = "",

    @ColumnInfo(name = "strMeal")
    val strMeal: String = "",

    @ColumnInfo(name = "strMealThumb")
    val strMealThumb: String = "",

    @ColumnInfo(name = "strMeasure")
    val strMeasure: String = "",

    @ColumnInfo(name = "strSource")
    val strSource: String = "",

    @ColumnInfo(name = "strTags")
    val strTags: String = "",

    @ColumnInfo(name = "strYoutube")
    val strYoutube: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


)
