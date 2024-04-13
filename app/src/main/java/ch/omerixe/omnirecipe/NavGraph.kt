package ch.omerixe.omnirecipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ch.omerixe.omnirecipe.detail.ui.navigateToRecipe
import ch.omerixe.omnirecipe.detail.ui.recipeDetailScreen
import ch.omerixe.omnirecipe.overview.ui.OverviewRoute
import ch.omerixe.omnirecipe.overview.ui.overviewScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = OverviewRoute
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        overviewScreen(onNavigateToRecipeDetail = { recipeId ->
            navController.navigateToRecipe(recipeId)
        })

        recipeDetailScreen(onNavigateUp = {
            navController.popBackStack()
        })
    }
}