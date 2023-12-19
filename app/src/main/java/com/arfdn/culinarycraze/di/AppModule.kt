package com.arfdn.culinarycraze.di

import com.arfdn.core.domain.usecase.MealInteractor
import com.arfdn.core.domain.usecase.MealUseCase
import com.arfdn.culinarycraze.ui.screen.detail.DetailViewModel
import com.arfdn.culinarycraze.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MealUseCase> { MealInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get())}
}