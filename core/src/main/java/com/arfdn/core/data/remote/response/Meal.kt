package com.arfdn.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal")
    val idMeal: String = "",
    @SerializedName("strMeal")
    val strMeal: String = "",
    @SerializedName("strMealThumb")
    val strMealThumb: String = "",
)