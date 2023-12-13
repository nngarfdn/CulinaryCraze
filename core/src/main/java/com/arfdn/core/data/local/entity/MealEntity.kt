package com.arfdn.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMeal")
    val idMeal: String = "",

    @ColumnInfo(name = "strMeal")
    val strMeal: String = "",

    @ColumnInfo(name = "strMealThumb")
    val strMealThumb: String = "",

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)