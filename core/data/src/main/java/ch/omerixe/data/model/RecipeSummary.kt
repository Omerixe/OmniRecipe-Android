package ch.omerixe.data.model

import ch.omerixe.data.model.external.RecipeSummary
import ch.omerixe.data.network.model.NetworkRecipeSummary

fun NetworkRecipeSummary.toExternalRecipeSummary() = RecipeSummary(
    id,
    title,
    subtitle,
    imageUrl
)