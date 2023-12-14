package com.arfdn.culinarycraze.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.arfdn.core.domain.usecase.MealUseCase


class HomeViewModel(private val mealUseCase: MealUseCase): ViewModel() {
    fun getAllMeals(area: String) = mealUseCase.getAllMeals(area).asLiveData()
}