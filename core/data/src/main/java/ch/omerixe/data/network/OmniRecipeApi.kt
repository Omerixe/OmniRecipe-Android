package ch.omerixe.data.network

import ch.omerixe.data.network.model.NetworkRecipe
import ch.omerixe.data.network.model.NetworkRecipeSummary

interface OmniRecipeApi {

    suspend fun getRecipeSummaries(): List<NetworkRecipeSummary>

    suspend fun getRecipes(): List<NetworkRecipe>

    suspend fun getRecipe(id: String): NetworkRecipe
}