package com.arfdn.favorite.ui.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.arfdn.core.data.Resource
import com.arfdn.core.domain.model.MealItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = koinViewModel(),
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite CulinaryCraze") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            favoriteViewModel.uiState.collectAsState(initial = Resource.Loading()).value.let { result ->
                when (result) {
                    is Resource.Loading -> {
                        favoriteViewModel.getAllFavoriteMeals()
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

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    item: MealItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = item.strMealThumb,
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.strMeal,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ListItem(
    listItem: List<MealItem>,
    navController: NavController,
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