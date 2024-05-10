package ch.omerixe.data.model

import ch.omerixe.data.model.external.Recipe
import ch.omerixe.data.network.model.NetworkIngredient
import ch.omerixe.data.network.model.NetworkRecipe

fun NetworkRecipe.toExternalRecipe() = Recipe(
    id,
    title,
    subtitle,
    ingredients.map(NetworkIngredient::toExternalIngredient),
    steps,
    imageUrl,
    version
)