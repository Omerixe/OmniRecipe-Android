package ch.omerixe.data.model

import ch.omerixe.data.database.model.DatabaseIngredient
import ch.omerixe.data.database.model.RecipeEntity
import ch.omerixe.data.model.external.Ingredient
import ch.omerixe.data.model.external.Recipe
import ch.omerixe.data.network.model.NetworkIngredient
import ch.omerixe.data.network.model.NetworkRecipe

internal fun NetworkRecipe.toExternalRecipe() = Recipe(
    id,
    title,
    subtitle,
    ingredients.map(NetworkIngredient::toExternalIngredient),
    steps,
    imageUrl,
    version
)

internal fun Recipe.toEntity() = RecipeEntity(
    id,
    title,
    subtitle,
    ingredients.map(Ingredient::toDatabaseIngredient),
    steps,
    imageUrl,
    version
)

internal fun Ingredient.toDatabaseIngredient() = DatabaseIngredient(
    name,
    quantity,
    unit
)

internal fun RecipeEntity.toExternalRecipe() = Recipe(
    id,
    title,
    subtitle,
    ingredients.map(DatabaseIngredient::toExternalIngredient),
    steps,
    imageUrl,
    version
)

internal fun DatabaseIngredient.toExternalIngredient() = Ingredient(
    quantity,
    name,
    unit
)