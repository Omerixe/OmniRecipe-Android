package ch.omerixe.data.domain.mock

import android.content.Context
import ch.omerixe.data.R
import ch.omerixe.data.domain.OmniRecipeApi
import ch.omerixe.data.model.Recipe
import ch.omerixe.data.model.RecipeSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OmniRecipeMockApi @Inject constructor(
    @ApplicationContext private val context: Context
) : OmniRecipeApi {
    private val recipes: MutableList<Recipe> = mutableListOf()

    init {
        val recipesJson = context.resources.openRawResource(R.raw.recipes)
            .bufferedReader()
            .use { it.readText() }
        val parsedRecipes = Json.decodeFromString<List<Recipe>>(recipesJson).map {
            it.copy(imageUrl = "android.resource://${context.packageName}/raw/${it.imageUrl}")
        }
        recipes.addAll(parsedRecipes)
    }

    override suspend fun getRecipeSummaries(): List<RecipeSummary> {
        delay(300)
        return recipes.map { RecipeSummary(it.id, it.title, it.subtitle, it.imageUrl) }
    }

    override suspend fun getRecipes(): List<Recipe> {
        delay(300)
        return recipes
    }

    override suspend fun getRecipe(id: String): Recipe {
        delay(300)
        return recipes.first { it.id == id }
    }

}