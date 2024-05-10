package ch.omerixe.recipedetail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.omerixe.data.domain.RecipeRepository
import ch.omerixe.data.model.network.NetworkIngredient
import ch.omerixe.data.model.network.NetworkRecipe
import ch.omerixe.ui.RecipeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

private const val TAG = "RecipeDetailViewModel"

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
            recipeRepository.getRecipe(recipeId).fold(
                onSuccess = { recipe ->
                    _uiState.value = UiState.Content(recipe.toUi())
                },
                onFailure = {
                    // We can do more error handling here
                    Log.d(TAG, "Error fetching recipe", it)
                    _uiState.value = UiState.Error(UiError.UNSPECIFIED)
                }
            )
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipeDetail: RecipeDetail) : UiState()

        data class Error(val type: UiError) : UiState()
    }

    enum class UiError {
        UNSPECIFIED
    }
}

private fun NetworkRecipe.toUi() = RecipeDetail(
    title = title,
    subtitle = subtitle,
    ingredients = ingredients.map { it.toUi() },
    steps = steps,
    recipeImage = RecipeImage.External(imageUrl),
)

private fun NetworkIngredient.toUi() =
    Ingredient(name, DecimalFormat("###.##").format(quantity), unit)