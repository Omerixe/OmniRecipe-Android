package ch.omerixe.omnirecipe.overview.ui

data class RecipeOverview(
    val id: String,
    val title: String,
    val subtitle: String,
    val recipeImage: RecipeImage
)

sealed class RecipeImage {
    data class External(val url: String) : RecipeImage()
    data class Internal(val resId: Int) : RecipeImage()
}