package ch.omerixe.recipedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.omerixe.data.domain.RecipeRepository
import ch.omerixe.data.model.Recipe
import ch.omerixe.ui.RecipeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val recipeId: String = RecipeArgs(savedStateHandle).recipeId

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val recipe = recipeRepository.getRecipe(recipeId)
            _uiState.value = UiState.Content(recipe.toUi())
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipeDetail: RecipeDetail) : UiState()
    }
}

private fun Recipe.toUi() = RecipeDetail(
    title = title,
    subtitle = subtitle,
    ingredients = ingredients.map { it.toUi() },
    steps = steps,
    recipeImage = RecipeImage.External(imageUrl),
)

private fun ch.omerixe.data.model.Ingredient.toUi() = Ingredient(name, quantity.toString(), unit)