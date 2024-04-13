package ch.omerixe.omnirecipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ch.omerixe.overview.ui.overviewScreen
import ch.omerixe.recipedetail.ui.navigateToRecipe
import ch.omerixe.recipedetail.ui.recipeDetailScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ch.omerixe.overview.ui.OverviewRoute
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