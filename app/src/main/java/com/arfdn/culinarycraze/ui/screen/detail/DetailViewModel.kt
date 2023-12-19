package com.arfdn.culinarycraze.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arfdn.core.data.Resource
import com.arfdn.core.data.remote.response.DetailMealResponse
import com.arfdn.core.domain.model.MealDetail
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.domain.usecase.MealUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val mealUseCase: MealUseCase): ViewModel(){

    private val _uiState: MutableStateFlow<Resource<DetailMealResponse>> = MutableStateFlow(Resource.Loading())
    val uiState: MutableStateFlow<Resource<DetailMealResponse>>
        get() = _uiState

    private val _uiStateFavorite: MutableStateFlow<Resource<MealItem>> = MutableStateFlow(Resource.Loading())
    val uiStateFavorite: MutableStateFlow<Resource<MealItem>>
        get() = _uiStateFavorite

    fun getDetailMeal(idMeal: String){
        viewModelScope.launch {
            mealUseCase.getDetailMeal(idMeal).asLiveData().observeForever {
                _uiState.value = it
            }
        }
    }

    fun setFavoriteMeal(mealItem: MealItem, state: Boolean){
        viewModelScope.launch {
            mealUseCase.setFavoriteMeal(mealItem, state)
        }
    }

    fun getFavoriteMealById(idMeal: String){
        viewModelScope.launch {
            mealUseCase.getFavoriteMealById(idMeal)?.asLiveData()?.observeForever {
                _uiStateFavorite.value = Resource.Success(it ?: MealItem())
            }
        }
    }



}