package ch.omerixe.omnirecipe.overview.ui

data class RecipeOverview(
    val title: String,
    val subtitle: String,
    val imageUrl: RecipeImage
)

sealed class RecipeImage {
    data class External(val url: String) : RecipeImage()
    data class Internal(val resId: Int) : RecipeImage()
}