package com.arfdn.core.data.remote.network

import com.arfdn.core.data.remote.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php")
    suspend fun getAllMeals(@Query("a") area: String): MealsResponse
}