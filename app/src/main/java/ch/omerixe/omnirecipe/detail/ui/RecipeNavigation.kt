package ch.omerixe.omnirecipe.detail.ui

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
        // Here comes the viewmodel implementation, it can read the recipeid from the savedStateHandle
        RecipeDetailScreen(onNavigateUp = onNavigateUp)
    }
}

fun NavController.navigateToRecipe(recipeId: String) {
    navigate("recipe/$recipeId")
}