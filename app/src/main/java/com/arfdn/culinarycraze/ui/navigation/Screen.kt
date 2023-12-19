package com.arfdn.culinarycraze.ui.navigation

sealed class Screen (val route: String){
    object Home: Screen("home")
    object Detail: Screen("detail/{idMeal}"){
        fun createRoute(idMeal: String): String = "detail/$idMeal"
    }
    object Favorite: Screen("favorite")
}