package ch.omerixe.data.domain

import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipe
import javax.inject.Inject


class RecipeRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipes(): List<NetworkRecipe> {
        return omniRecipeApi.getRecipes()
    }

    suspend fun getRecipe(id: String): Result<NetworkRecipe> =
        runCatching { omniRecipeApi.getRecipe(id) }

}

