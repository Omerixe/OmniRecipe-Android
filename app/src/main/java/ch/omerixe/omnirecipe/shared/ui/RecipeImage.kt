package ch.omerixe.omnirecipe.shared.ui

sealed class RecipeImage {
    data class External(val url: String) : RecipeImage()
    data class Internal(val resId: Int) : RecipeImage()
}

fun RecipeImage.imageModel(): Any {
    return when (this) {
        is RecipeImage.External -> url
        is RecipeImage.Internal -> resId
    }
}