package ch.omerixe.recipedetail.ui

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val recipeIdArg = "recipeId"

internal class RecipeArgs(val recipeId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[recipeIdArg]) as String
    )
}

fun NavGraphBuilder.recipeDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable("recipe/{$recipeIdArg}") { _ ->
        val viewModel: RecipeDetailViewModel = hiltViewModel()
        val uiState = viewModel.uiState.collectAsState()
        RecipeDetailScreen(
            uiState = uiState.value,
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToRecipe(recipeId: String) {
    navigate("recipe/$recipeId")
}