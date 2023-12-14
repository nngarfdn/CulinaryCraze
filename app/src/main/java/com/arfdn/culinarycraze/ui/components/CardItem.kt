package com.arfdn.culinarycraze.ui.components

import androidx.compose.runtime.Composable
import com.arfdn.core.data.remote.response.Meal
import com.arfdn.core.domain.model.MealItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun CardItem(
    item: MealItem,
//    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small)
            .clickable {
//                navController.navigate(Screen.DetailDestination.createRoute(destination.id))
                       },
    ) {
        Column(
            modifier = modifier
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
