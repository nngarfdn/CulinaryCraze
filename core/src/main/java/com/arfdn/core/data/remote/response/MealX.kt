package com.arfdn.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class MealX(
    @SerializedName("dateModified")
    val dateModified: String? = String(),
    @SerializedName("idMeal")
    val idMeal: String = "",
    @SerializedName("strArea")
    val strArea: String = "",
    @SerializedName("strCategory")
    val strCategory: String = "",
    @SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? = String(),
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: String? = String(),
    @SerializedName("strImageSource")
    val strImageSource: String? = String(),
    @SerializedName("strIngredient1")
    val strIngredient1: String = "",
    @SerializedName("strIngredient10")
    val strIngredient10: String = "",
    @SerializedName("strIngredient11")
    val strIngredient11: String = "",
    @SerializedName("strIngredient12")
    val strIngredient12: String = "",
    @SerializedName("strIngredient13")
    val strIngredient13: String = "",
    @SerializedName("strIngredient14")
    val strIngredient14: String = "",
    @SerializedName("strIngredient15")
    val strIngredient15: String = "",
    @SerializedName("strIngredient16")
    val strIngredient16: String = "",
    @SerializedName("strIngredient17")
    val strIngredient17: String = "",
    @SerializedName("strIngredient18")
    val strIngredient18: String = "",
    @SerializedName("strIngredient19")
    val strIngredient19: String = "",
    @SerializedName("strIngredient2")
    val strIngredient2: String = "",
    @SerializedName("strIngredient20")
    val strIngredient20: String = "",
    @SerializedName("strIngredient3")
    val strIngredient3: String = "",
    @SerializedName("strIngredient4")
    val strIngredient4: String = "",
    @SerializedName("strIngredient5")
    val strIngredient5: String = "",
    @SerializedName("strIngredient6")
    val strIngredient6: String = "",
    @SerializedName("strIngredient7")
    val strIngredient7: String = "",
    @SerializedName("strIngredient8")
    val strIngredient8: String = "",
    @SerializedName("strIngredient9")
    val strIngredient9: String = "",
    @SerializedName("strInstructions")
    val strInstructions: String = "",
    @SerializedName("strMeal")
    val strMeal: String = "",
    @SerializedName("strMealThumb")
    val strMealThumb: String = "",
    @SerializedName("strMeasure1")
    val strMeasure1: String = "",
    @SerializedName("strMeasure10")
    val strMeasure10: String = "",
    @SerializedName("strMeasure11")
    val strMeasure11: String = "",
    @SerializedName("strMeasure12")
    val strMeasure12: String = "",
    @SerializedName("strMeasure13")
    val strMeasure13: String = "",
    @SerializedName("strMeasure14")
    val strMeasure14: String = "",
    @SerializedName("strMeasure15")
    val strMeasure15: String = "",
    @SerializedName("strMeasure16")
    val strMeasure16: String = "",
    @SerializedName("strMeasure17")
    val strMeasure17: String = "",
    @SerializedName("strMeasure18")
    val strMeasure18: String = "",
    @SerializedName("strMeasure19")
    val strMeasure19: String = "",
    @SerializedName("strMeasure2")
    val strMeasure2: String = "",
    @SerializedName("strMeasure20")
    val strMeasure20: String = "",
    @SerializedName("strMeasure3")
    val strMeasure3: String = "",
    @SerializedName("strMeasure4")
    val strMeasure4: String = "",
    @SerializedName("strMeasure5")
    val strMeasure5: String = "",
    @SerializedName("strMeasure6")
    val strMeasure6: String = "",
    @SerializedName("strMeasure7")
    val strMeasure7: String = "",
    @SerializedName("strMeasure8")
    val strMeasure8: String = "",
    @SerializedName("strMeasure9")
    val strMeasure9: String = "",
    @SerializedName("strSource")
    val strSource: String = "",
    @SerializedName("strTags")
    val strTags: String = "",
    @SerializedName("strYoutube")
    val strYoutube: String = ""
)

fun MealX.getIngredientsAsString(): String {
    val ingredientsList = mutableListOf<String>()

    for (i in 1..20) {
        val ingredient = getIngredient(i)
        if (ingredient.isNotEmpty()) {
            ingredientsList.add(ingredient)
        }
    }

    return ingredientsList.joinToString(", ")
}

fun MealX.getIngredient(index: Int): String {
    return when (index) {
        1 -> strIngredient1
        2 -> strIngredient2
        3 -> strIngredient3
        4 -> strIngredient4
        5 -> strIngredient5
        6 -> strIngredient6
        7 -> strIngredient7
        8 -> strIngredient8
        9 -> strIngredient9
        10 -> strIngredient10
        11 -> strIngredient11
        12 -> strIngredient12
        13 -> strIngredient13
        14 -> strIngredient14
        15 -> strIngredient15
        16 -> strIngredient16
        17 -> strIngredient17
        18 -> strIngredient18
        19 -> strIngredient19
        20 -> strIngredient20
        else -> ""
    }
}

fun MealX.getMeasuresAsString(): String {
    val measuresList = mutableListOf<String>()

    for (i in 1..20) {
        val measure = getMeasure(i)
        if (measure.isNotEmpty()) {
            measuresList.add(measure)
        }
    }

    return measuresList.joinToString(", ")
}

fun MealX.getMeasure(index: Int): String {
    return when (index) {
        1 -> strMeasure1
        2 -> strMeasure2
        3 -> strMeasure3
        4 -> strMeasure4
        5 -> strMeasure5
        6 -> strMeasure6
        7 -> strMeasure7
        8 -> strMeasure8
        9 -> strMeasure9
        10 -> strMeasure10
        11 -> strMeasure11
        12 -> strMeasure12
        13 -> strMeasure13
        14 -> strMeasure14
        15 -> strMeasure15
        16 -> strMeasure16
        17 -> strMeasure17
        18 -> strMeasure18
        19 -> strMeasure19
        20 -> strMeasure20
        else -> ""
    }
}
