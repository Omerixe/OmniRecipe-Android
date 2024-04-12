package ch.omerixe.omnirecipe.overview.ui

import ch.omerixe.omnirecipe.shared.ui.RecipeImage

data class RecipeOverview(
    val id: String,
    val title: String,
    val recipeImage: RecipeImage
)