package com.arfdn.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal> = listOf()
)