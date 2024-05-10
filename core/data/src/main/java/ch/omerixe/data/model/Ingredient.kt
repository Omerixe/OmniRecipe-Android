package ch.omerixe.data.model

import ch.omerixe.data.model.external.Ingredient
import ch.omerixe.data.network.model.NetworkIngredient

fun NetworkIngredient.toExternalIngredient() = Ingredient(
    quantity,
    unit,
    name
)