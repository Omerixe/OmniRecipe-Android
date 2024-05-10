package ch.omerixe.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkRecipe(
    val id: String,
    val title: String,
    val subtitle: String,
    val ingredients: List<NetworkIngredient>,
    val steps: List<String>,
    val imageUrl: String,
    val version: Int
)