package com.arfdn.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class DetailMealResponse(
    @SerializedName("meals")
    val meals: List<MealX> = listOf()
)