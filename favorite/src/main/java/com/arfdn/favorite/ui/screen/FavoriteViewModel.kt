package com.arfdn.favorite.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arfdn.core.data.Resource
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.domain.usecase.MealUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mealUseCase: MealUseCase): ViewModel() {

    private val _uiState: MutableStateFlow<Resource<List<MealItem>>> = MutableStateFlow(Resource.Loading())
    val uiState: StateFlow<Resource<List<MealItem>>>
        get() = _uiState

    fun getAllFavoriteMeals(){
        viewModelScope.launch {
            mealUseCase.getFavoriteMeals().asLiveData().observeForever {
                _uiState.value = Resource.Success(it)
            }
        }
    }

}