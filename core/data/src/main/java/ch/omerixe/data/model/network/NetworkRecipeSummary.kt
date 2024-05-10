package ch.omerixe.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkRecipeSummary(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)
