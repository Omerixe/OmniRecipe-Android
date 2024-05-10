package ch.omerixe.data.model

import ch.omerixe.data.database.model.DatabaseIngredient
import ch.omerixe.data.database.model.RecipeEntity
import ch.omerixe.data.model.external.Ingredient
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

fun Recipe.toEntity() = RecipeEntity(
    id,
    title,
    subtitle,
    ingredients.map(Ingredient::toDatabaseIngredient),
    steps,
    imageUrl,
    version
)

fun Ingredient.toDatabaseIngredient() = DatabaseIngredient(
    name,
    quantity,
    unit
)

fun RecipeEntity.toExternalRecipe() = Recipe(
    id,
    title,
    subtitle,
    ingredients.map(DatabaseIngredient::toExternalIngredient),
    steps,
    imageUrl,
    version
)

fun DatabaseIngredient.toExternalIngredient() = Ingredient(
    quantity,
    name,
    unit
)