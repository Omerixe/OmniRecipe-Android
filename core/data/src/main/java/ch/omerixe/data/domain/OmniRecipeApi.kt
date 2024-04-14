package ch.omerixe.data.domain

import ch.omerixe.data.model.Recipe
import ch.omerixe.data.model.RecipeSummary

interface OmniRecipeApi {

    suspend fun getRecipeSummaries(): List<RecipeSummary>

    suspend fun getRecipes(): List<Recipe>

    suspend fun getRecipe(id: String): Recipe
}