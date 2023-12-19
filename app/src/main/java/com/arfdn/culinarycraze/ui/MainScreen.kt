package com.arfdn.culinarycraze.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arfdn.culinarycraze.ui.navigation.Screen
import com.arfdn.culinarycraze.ui.screen.detail.DetailScreen
import com.arfdn.culinarycraze.ui.screen.home.HomeScreen
import com.arfdn.favorite.ui.screen.FavoriteScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(navController = navController)
            }
            composable(Screen.Favorite.route){
                FavoriteScreen(navController = navController)
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("idMeal") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("idMeal") ?: ""
                DetailScreen(idMeal = id)
            }
        }
    }

}