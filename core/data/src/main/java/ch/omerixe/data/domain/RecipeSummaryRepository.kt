package ch.omerixe.data.domain

import ch.omerixe.data.model.RecipeSummary
import javax.inject.Inject

class RecipeSummaryRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipeSummaries(): Result<List<RecipeSummary>> =
        runCatching { omniRecipeApi.getRecipeSummaries() }

}