package ch.omerixe.overview.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.overviewScreen(
    onNavigateToRecipeDetail: (recipeId: String) -> Unit
) {
    composable("overview") {
        val viewModel: OverviewViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsState()
        OverviewScreen(
            uiState = uiState.value,
            onNavigateToRecipeDetail = onNavigateToRecipeDetail
        )
    }
}

const val OverviewRoute = "overview"
fun NavController.navigateToOverview() {
    navigate(OverviewRoute)
}