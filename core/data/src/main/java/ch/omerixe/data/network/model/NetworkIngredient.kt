package ch.omerixe.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkIngredient(
    val quantity: Double,
    val unit: String,
    val name: String
)