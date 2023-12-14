package com.arfdn.culinarycraze.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arfdn.core.data.Resource
import com.arfdn.core.domain.model.MealItem
import com.arfdn.culinarycraze.ui.components.CardItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

    homeViewModel.uiState.collectAsState(initial = Resource.Loading()).value.let { result ->
        when (result) {
            is Resource.Loading -> {
                Log.d("getAllMeals", "Loading")
                homeViewModel.getAllMeals("Canadian")
            }

            is Resource.Success -> {
                Log.d("getAllMeals", "Success")
                if (result.data != null) {
                    HomeContent(listItem = result.data!!)
                } else {
                    Log.d("getAllMeals", "Data is null")
                }
            }

            is Resource.Error -> {
                Log.d("getAllMeals", "Error: ${result.message}")
            }
        }
    }

}

@Composable
fun HomeContent(
    listItem: List<MealItem>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(listItem) { data ->
            CardItem(
                item = data
            )
        }
    }
}