package com.arfdn.culinarycraze

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arfdn.core.data.Resource
import com.arfdn.culinarycraze.ui.screen.home.HomeViewModel
import com.arfdn.culinarycraze.ui.theme.CulinaryCrazeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel



class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinaryCrazeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        homeViewModel.getAllMeals("Canadian").observe(this) { meals ->
            if (meals != null) {
                when (meals) {
                    is Resource.Loading -> {
                        Log.d("getAllMeals", "Loading")
                    }

                    is Resource.Success -> {
                        Log.d("getAllMeals", "Success")
                        val data = meals.data
                        for (meal in data!!) {
                            Log.d("getAllMeals", "Meal: ${meal.strMeal}")
                        }
                    }

                    is Resource.Error -> {
                        Log.d("getAllMeals", "Error: ${meals.message}")
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CulinaryCrazeTheme {
        Greeting("Android")
    }
}