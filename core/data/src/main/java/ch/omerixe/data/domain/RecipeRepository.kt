package ch.omerixe.data.domain

import ch.omerixe.data.model.Recipe
import javax.inject.Inject


class RecipeRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipes(): List<Recipe> {
        return omniRecipeApi.getRecipes()
    }

    suspend fun getRecipe(id: String): Recipe {
        return omniRecipeApi.getRecipe(id)
    }

}

