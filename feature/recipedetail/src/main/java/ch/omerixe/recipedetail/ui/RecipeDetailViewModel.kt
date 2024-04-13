package ch.omerixe.recipedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.omerixe.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recipeId: String = RecipeArgs(savedStateHandle).recipeId

    private val recipeDetail = RecipeDetail(
        "Banana Smoothie",
        "A delicious and refreshing smoothie",
        ch.omerixe.ui.RecipeImage.Internal(R.drawable.banana),
        listOf(
            Ingredient("Bananas", "2", "pcs"),
            Ingredient("Honey", "1", "tbsp"),
            Ingredient("Milk", "1", "cup"),
            Ingredient("Ice", "1", "cup"),
        ),
        listOf(
            "Peel the bananas",
            "Add the bananas to the blender",
            "Add the honey, milk, and ice to the blender",
            "Blend until smooth",
        ),
    )

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        // Simulate receiving the recipe detail from a network request
        _uiState.value = UiState.Content(recipeDetail)
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipeDetail: RecipeDetail) : UiState()
    }
}