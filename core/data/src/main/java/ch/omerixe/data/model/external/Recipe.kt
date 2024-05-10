package ch.omerixe.data.model.external

data class Recipe(
    val id: String,
    val title: String,
    val subtitle: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val imageUrl: String,
    val version: Int
)