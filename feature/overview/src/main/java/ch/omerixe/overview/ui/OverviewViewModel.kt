package ch.omerixe.overview.ui

import androidx.lifecycle.ViewModel
import ch.omerixe.ui.R
import ch.omerixe.ui.RecipeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class OverviewViewModel @Inject constructor() : ViewModel() {
    private val recipes = listOf(
        RecipeOverview(
            "1",
            "Test Recipe 1",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "2",
            "Test Recipe 2",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "3",
            "Test Recipe 3",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "4",
            "Test Recipe 4",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "5",
            "Test Recipe 5",
            RecipeImage.Internal(R.drawable.banana)
        ),
    )

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        // Simulate receiving the recipes from a network request
        _uiState.value = UiState.Content(recipes)
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipes: List<RecipeOverview>) : UiState()
    }
}