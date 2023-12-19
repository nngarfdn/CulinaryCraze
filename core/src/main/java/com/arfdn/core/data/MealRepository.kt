package com.arfdn.core.data

import android.util.Log
import com.arfdn.core.data.local.LocalDataSource
import com.arfdn.core.data.remote.RemoteDataSource
import com.arfdn.core.data.remote.network.ApiResponse
import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.data.remote.response.MealsResponse
import com.arfdn.core.domain.model.MealDetail
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.domain.repository.IMealRepository
import com.arfdn.core.utils.AppExecutors
import com.arfdn.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MealRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMealRepository {
    override fun getAllMeals(area: String): Flow<Resource<List<MealItem>>> =
        object : NetworkBoundResource<List<MealItem>, MealsResponse>() {
            override fun loadFromDB(): Flow<List<MealItem>> {
                return localDataSource.getAllMeals().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<MealsResponse>> {
                return remoteDataSource.getAllMeals(area)
            }

            override suspend fun saveCallResult(data: MealsResponse) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMeals(tourismList)
            }

            override fun shouldFetch(data: List<MealItem>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()



    override fun getDetailMeal(idMeal: String): Flow<Resource<DetailMealResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = remoteDataSource.getDetailMeal(idMeal)
                response.collect{ value ->
                    when (value) {
                        is ApiResponse.Success -> {
                            emit(Resource.Success(value.data))
                        }
                        is ApiResponse.Empty -> {
                            emit(Resource.Error("Empty"))
                        }
                        is ApiResponse.Error -> {
                            emit(Resource.Error(value.errorMessage))
                        }
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    override fun getFavoriteMeals(): Flow<List<MealItem>> {
        return localDataSource.getFavoriteMeals().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMealById(idMeal: String): Flow<MealItem>? {
        return localDataSource.getFavoriteMealById(idMeal)?.map {
            DataMapper.mapEntityToDomain(it)
        }
    }


    override fun setFavoriteMeal(item: MealItem, state: Boolean) {
        val item = DataMapper.mapDomainToEntity(item)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMeal(item, state) }
    }

}