package ch.omerixe.overview.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object OverviewRoute

fun NavGraphBuilder.overviewScreen(
    onNavigateToRecipeDetail: (recipeId: String, recipeTitle: String) -> Unit
) {
    composable<OverviewRoute> {
        val viewModel: OverviewViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsState()
        OverviewScreen(
            uiState = uiState.value,
            onNavigateToRecipeDetail = onNavigateToRecipeDetail
        )
    }
}
