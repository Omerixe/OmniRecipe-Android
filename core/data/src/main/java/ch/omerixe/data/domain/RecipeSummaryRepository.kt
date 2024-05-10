package ch.omerixe.data.domain

import ch.omerixe.data.model.network.NetworkRecipeSummary
import javax.inject.Inject

class RecipeSummaryRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipeSummaries(): Result<List<NetworkRecipeSummary>> =
        runCatching { omniRecipeApi.getRecipeSummaries() }

}