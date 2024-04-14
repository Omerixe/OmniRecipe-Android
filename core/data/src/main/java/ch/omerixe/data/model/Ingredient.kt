package ch.omerixe.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val quantity: Int,
    val unit: String,
    val name: String
)