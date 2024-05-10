package ch.omerixe.data.domain

import ch.omerixe.data.model.external.Recipe
import ch.omerixe.data.model.toExternalRecipe
import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipe
import javax.inject.Inject


class RecipeRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipes(): List<Recipe> {
        return omniRecipeApi.getRecipes().map(NetworkRecipe::toExternalRecipe)
    }

    suspend fun getRecipe(id: String): Result<Recipe> =
        runCatching { omniRecipeApi.getRecipe(id).toExternalRecipe() }

}

