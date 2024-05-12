package ch.omerixe.recipedetail.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailRoute(val id: String)

fun NavGraphBuilder.recipeDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<RecipeDetailRoute> { _ ->
        val viewModel: RecipeDetailViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsState()
        RecipeDetailScreen(
            uiState = uiState.value,
            onNavigateUp = onNavigateUp
        )
    }
}