package ch.omerixe.data.domain

import ch.omerixe.data.model.external.RecipeSummary
import ch.omerixe.data.model.toExternalRecipeSummary
import ch.omerixe.data.network.OmniRecipeApi
import ch.omerixe.data.network.model.NetworkRecipeSummary
import javax.inject.Inject

class RecipeSummaryRepository @Inject constructor(private val omniRecipeApi: OmniRecipeApi) {

    suspend fun getRecipeSummaries(): Result<List<RecipeSummary>> =
        runCatching {
            omniRecipeApi.getRecipeSummaries().map(NetworkRecipeSummary::toExternalRecipeSummary)
        }

}