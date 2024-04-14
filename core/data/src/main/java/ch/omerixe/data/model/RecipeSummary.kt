package ch.omerixe.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeSummary(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)
