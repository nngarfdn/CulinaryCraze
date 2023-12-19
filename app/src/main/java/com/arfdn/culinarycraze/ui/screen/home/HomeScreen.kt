package com.arfdn.culinarycraze.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arfdn.core.data.Resource
import com.arfdn.core.domain.model.MealItem
import com.arfdn.culinarycraze.ui.components.CardItem
import com.arfdn.culinarycraze.ui.components.ListItem
import com.arfdn.culinarycraze.ui.components.Loading
import com.arfdn.culinarycraze.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "CulinaryCraze") },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Favorite.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            homeViewModel.uiState.collectAsState(initial = Resource.Loading()).value.let { result ->
                when (result) {
                    is Resource.Loading -> {
                        homeViewModel.getAllMeals("Canadian")
                        Loading()
                    }

                    is Resource.Success -> {
                        Log.d("getAllMeals", "Success")
                        if (result.data != null) {
                            ListItem(listItem = result.data!!, navController = navController)
                        } else {
                            Log.d("getAllMeals", "Data is null")
                        }
                    }

                    is Resource.Error -> {
                        Log.e("getAllMeals", "Error: ${result.message}")
                    }
                }
            }
        }
    }



}

