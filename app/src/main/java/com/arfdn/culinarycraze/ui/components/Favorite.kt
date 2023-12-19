package com.arfdn.culinarycraze.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(isFavorite: Boolean, onToggleFavorite: () -> Unit) {
    val isFavorite = remember { mutableStateOf(isFavorite) }

    IconButton(onClick = {
        isFavorite.value = !isFavorite.value
        onToggleFavorite()
    }) {
        Icon(
            imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite Button",
            tint = if (isFavorite.value) Color.Red else Color.Gray
        )
    }
}