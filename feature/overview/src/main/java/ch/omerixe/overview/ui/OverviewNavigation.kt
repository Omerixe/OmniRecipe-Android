package ch.omerixe.overview.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.overviewScreen(
    onNavigateToRecipeDetail: (recipeId: String) -> Unit
) {
    composable("overview") {
        // Here comes the viewmodel implementation
        OverviewScreen(
            onNavigateToRecipeDetail = onNavigateToRecipeDetail
        )
    }
}

const val OverviewRoute = "overview"
fun NavController.navigateToOverview() {
    navigate(OverviewRoute)
}