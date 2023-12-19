package com.arfdn.culinarycraze.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arfdn.core.data.Resource
import com.arfdn.core.data.remote.response.MealX
import com.arfdn.core.data.remote.response.getIngredientsAsString
import com.arfdn.core.data.remote.response.getMeasuresAsString
import com.arfdn.core.domain.model.MealItem
import com.arfdn.core.utils.DataMapper
import com.arfdn.culinarycraze.ui.components.FavoriteButton
import com.arfdn.culinarycraze.ui.components.Loading
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    idMeal: String,
    detailViewModel: DetailViewModel = koinViewModel()
) {
    val itemMeal = remember { mutableStateOf(MealItem()) }
    val mealX = remember { mutableStateOf(MealX()) }


    // Fetch meal details
    LaunchedEffect(idMeal) {
        detailViewModel.getDetailMeal(idMeal)
    }

    // Observe meal details
    val mealDetailResult by detailViewModel.uiState.collectAsState(initial = Resource.Loading())
    when (mealDetailResult) {
        is Resource.Success -> {
            val mealDetail = (mealDetailResult as Resource.Success).data
            Log.d("DetailScreen", "Success")
            Log.d("DetailScreen", mealDetail.toString())

            val dataItem = mealDetail?.let { DataMapper.mapMealDetailResponseToMealItem(it) }
            itemMeal.value = dataItem!!
            mealX.value = mealDetail.meals[0]
        }

        is Resource.Error -> {
            Log.d("DetailScreen", "Error: ${(mealDetailResult as Resource.Error).message}")
            (mealDetailResult as Resource.Error).message?.let { Text(text = it) }
        }

        else -> {}
    }

    // Fetch and observe favorite status
    LaunchedEffect(idMeal) {
        detailViewModel.getFavoriteMealById(idMeal)
    }

    val favoriteMealResult by detailViewModel.uiStateFavorite.collectAsState(initial = Resource.Loading())
    when (favoriteMealResult) {
        is Resource.Loading -> {
            Loading()
        }
        is Resource.Success -> {
            val mealFav = (favoriteMealResult as Resource.Success).data
            Log.d("DetailScreen", "room 1 ${mealFav?.idMeal}")
            Log.d("DetailScreen", "room 2 ${itemMeal.value.idMeal}")
            val isFavorite = mealFav?.idMeal == itemMeal.value.idMeal
            DetailContent(
                mealX = mealX.value,
                isFavorite = !isFavorite,
                onToggleFavorite = {
                    if (!isFavorite) {
                        // Hapus dari daftar favorit
                        detailViewModel.setFavoriteMeal(itemMeal.value, true)
                    } else {
                        // Tambahkan ke daftar favorit
                        detailViewModel.setFavoriteMeal(itemMeal.value, false)
                    }
                    detailViewModel.getDetailMeal(idMeal)
                }
                )

        }

        is Resource.Error -> {
            Log.d("DetailScreen", "Error: ${(favoriteMealResult as Resource.Error).message}")
            (favoriteMealResult as Resource.Error).message?.let { Text(text = it) }
        }

        else -> {
            // Handle loading state if needed
        }
    }
}


@Composable
fun DetailContent(mealX: MealX, isFavorite: Boolean, onToggleFavorite: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Detail Meal",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
        }
        AsyncImage(
            model = mealX.strMealThumb,
            contentDescription = "Meal Thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .padding(vertical = 16.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = mealX.strMeal,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            FavoriteButton(
                isFavorite = isFavorite,
                onToggleFavorite = onToggleFavorite
            )
        }


        Text(text = "Area: ${mealX.strArea}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Category: ${mealX.strCategory}", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingredients: ${mealX.getIngredientsAsString()}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Measures: ${mealX.getMeasuresAsString()}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



