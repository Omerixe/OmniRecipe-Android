package ch.omerixe.data.domain.ktor

import ch.omerixe.data.domain.OmniRecipeApi
import ch.omerixe.data.model.network.NetworkRecipe
import ch.omerixe.data.model.network.NetworkRecipeSummary
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class OmniRecipeKtorApi @Inject constructor(private val httpClient: HttpClient) :
    OmniRecipeApi {

    override suspend fun getRecipes(): List<NetworkRecipe> {
        return httpClient.get("recipe").body()
    }

    override suspend fun getRecipe(id: String): NetworkRecipe {
        return httpClient.get("recipe/$id").body()
    }

    override suspend fun getRecipeSummaries(): List<NetworkRecipeSummary> {
        return httpClient.get("recipesummary").body()
    }

}