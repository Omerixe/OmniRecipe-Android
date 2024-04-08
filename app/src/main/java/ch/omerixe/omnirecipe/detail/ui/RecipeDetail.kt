package ch.omerixe.omnirecipe.detail.ui

import ch.omerixe.omnirecipe.shared.ui.RecipeImage

data class RecipeDetail(
    val title: String,
    val subtitle: String,
    val recipeImage: RecipeImage,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
)

data class Ingredient(
    val name: String,
    val quantity: String,
    val unit: String,
)