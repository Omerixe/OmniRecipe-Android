package ch.omerixe.data.network.mock

import android.content.Context
import ch.omerixe.data.R
import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipe
import ch.omerixe.data.network.model.NetworkRecipeSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class OmniRecipeMockApi @Inject constructor(
    @ApplicationContext private val context: Context
) : OmniRecipeApi {
    private val recipes: MutableList<NetworkRecipe> = mutableListOf()

    init {
        val recipesJson = context.resources.openRawResource(R.raw.recipes)
            .bufferedReader()
            .use { it.readText() }
        val parsedRecipes = Json.decodeFromString<List<NetworkRecipe>>(recipesJson).map {
            it.copy(imageUrl = "android.resource://${context.packageName}/raw/${it.imageUrl}")
        }
        recipes.addAll(parsedRecipes)
    }

    override suspend fun getRecipeSummaries(): List<NetworkRecipeSummary> {
        delay(300)
        return recipes.map { NetworkRecipeSummary(it.id, it.title, it.subtitle, it.imageUrl) }
    }

    override suspend fun getRecipes(): List<NetworkRecipe> {
        delay(300)
        return recipes
    }

    override suspend fun getRecipe(id: String): NetworkRecipe {
        delay(300)
        return recipes.first { it.id == id }
    }

    override suspend fun deleteRecipe(id: String) {
        delay(300)
    }

}