package com.arfdn.core.data

import com.arfdn.core.data.local.LocalDataSource
import com.arfdn.core.data.remote.RemoteDataSource
import com.arfdn.core.data.remote.network.ApiResponse
import com.arfdn.core.data.remote.response.MealsResponse
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.domain.repository.IMealRepository
import com.arfdn.core.utils.AppExecutors
import com.arfdn.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
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

    override fun getFavoriteMeals(): Flow<List<MealItem>> {
        return localDataSource.getFavoriteMeals().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMeal(tourism: MealItem, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMeal(tourismEntity, state) }
    }

}