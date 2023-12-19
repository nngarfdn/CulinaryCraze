package com.arfdn.core.data.remote.network

import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.data.remote.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php")
    suspend fun getAllMeals(@Query("a") area: String): MealsResponse

    @GET("lookup.php")
    suspend fun getDetailMeal(@Query("i") idMeal: String): DetailMealResponse
}