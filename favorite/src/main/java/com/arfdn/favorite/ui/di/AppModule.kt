package com.arfdn.favorite.ui.di

import com.arfdn.core.domain.usecase.MealInteractor
import com.arfdn.core.domain.usecase.MealUseCase
import com.arfdn.favorite.ui.screen.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModuleFavorite = module {
    factory<MealUseCase> { MealInteractor(get()) }
}

val viewModelModuleFavorite = module {
    viewModel { FavoriteViewModel(get()) }
}