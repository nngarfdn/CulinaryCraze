package com.arfdn.core.data.remote

import android.util.Log
import com.arfdn.core.data.remote.network.ApiResponse
import com.arfdn.core.data.remote.network.ApiService
import com.arfdn.core.data.remote.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMeals(area: String): Flow<ApiResponse<MealsResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAllMeals(area)
                if (response.meals.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}