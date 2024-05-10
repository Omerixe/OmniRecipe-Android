package ch.omerixe.data.domain

import ch.omerixe.data.database.OmniDatabase
import ch.omerixe.data.database.di.IoDispatcher
import ch.omerixe.data.database.model.RecipeEntity
import ch.omerixe.data.model.external.RecipeSummary
import ch.omerixe.data.model.toExternalRecipeSummary
import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipeSummary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeSummaryRepository @Inject constructor(
    private val omniRecipeApi: OmniRecipeApi,
    private val omniDatabase: OmniDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getRecipeSummaries(): Result<List<RecipeSummary>> {
        return withContext(ioDispatcher) {
            val result = runCatching { omniRecipeApi.getRecipeSummaries() }
            return@withContext result.fold(
                onSuccess = {
                    Result.success(it.map(NetworkRecipeSummary::toExternalRecipeSummary))
                },
                onFailure = {
                    val offlineRecipes = omniDatabase.recipeDao().getAll()
                    if (offlineRecipes.isNotEmpty()) {
                        Result.success(offlineRecipes.map(RecipeEntity::toExternalRecipeSummary))
                    } else {
                        Result.failure(it)
                    }
                }
            )
        }
    }
}

private fun RecipeEntity.toExternalRecipeSummary() = RecipeSummary(
    id = id,
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl
)
