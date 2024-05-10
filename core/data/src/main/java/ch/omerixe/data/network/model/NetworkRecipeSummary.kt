package ch.omerixe.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkRecipeSummary(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)
