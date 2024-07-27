package ch.omerixe.data.domain

import android.util.Log
import ch.omerixe.data.database.OmniDatabase
import ch.omerixe.data.database.di.IoDispatcher
import ch.omerixe.data.model.external.Recipe
import ch.omerixe.data.model.toEntity
import ch.omerixe.data.model.toExternalRecipe
import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "RecipeRepository"

class RecipeRepository @Inject constructor(
    private val omniRecipeApi: OmniRecipeApi,
    private val omniDatabase: OmniDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getRecipes(): List<Recipe> {
        return omniRecipeApi.getRecipes().map(NetworkRecipe::toExternalRecipe)
    }

    suspend fun getRecipe(id: String): Result<Recipe> {
        return withContext(ioDispatcher) {
            val result = runCatching { omniRecipeApi.getRecipe(id).toExternalRecipe() }
            return@withContext result.fold(
                onSuccess = {
                    omniDatabase.recipeDao()
                        .insertAll(it.toEntity())
                    Result.success(it)
                },
                onFailure = { throwable ->
                    Log.d(TAG, "Fallback to offline data", throwable)
                    val offlineRecipe = omniDatabase.recipeDao().findById(id)
                    offlineRecipe?.let { Result.success(it.toExternalRecipe()) } ?: Result.failure(
                        throwable
                    )
                }
            )
        }
    }

    suspend fun deleteRecipe(id: String) {
        withContext(ioDispatcher) {
            val result = runCatching { omniRecipeApi.deleteRecipe(id) }
            result.fold(
                onSuccess = {
                    val recipe = omniDatabase.recipeDao().findById(id)
                    recipe?.let { omniDatabase.recipeDao().delete(it) }
                },
                onFailure = { throwable ->
                    Log.d(TAG, "Fallback to offline data", throwable)
                }
            )
        }
    }

}

