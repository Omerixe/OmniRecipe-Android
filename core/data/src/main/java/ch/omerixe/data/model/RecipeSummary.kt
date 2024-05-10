package ch.omerixe.data.model

import ch.omerixe.data.model.external.RecipeSummary
import ch.omerixe.data.network.model.NetworkRecipeSummary

internal fun NetworkRecipeSummary.toExternalRecipeSummary() = RecipeSummary(
    id,
    title,
    subtitle,
    imageUrl
)