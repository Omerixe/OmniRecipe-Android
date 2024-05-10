package ch.omerixe.data.domain

import ch.omerixe.data.model.network.NetworkRecipe
import ch.omerixe.data.model.network.NetworkRecipeSummary

interface OmniRecipeApi {

    suspend fun getRecipeSummaries(): List<NetworkRecipeSummary>

    suspend fun getRecipes(): List<NetworkRecipe>

    suspend fun getRecipe(id: String): NetworkRecipe
}