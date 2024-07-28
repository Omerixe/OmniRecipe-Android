package ch.omerixe.recipedetail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import ch.omerixe.data.domain.Coordinator
import ch.omerixe.data.domain.RecipeRepository
import ch.omerixe.data.model.external.Ingredient
import ch.omerixe.data.model.external.Recipe
import ch.omerixe.ui.RecipeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

private const val TAG = "RecipeDetailViewModel"

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
    private val coordinator: Coordinator,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<RecipeDetailRoute>()
    private val recipeId: String = route.id
    private val recipeTitle: String = route.title

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading(recipeTitle))
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {
        viewModelScope.launch {
            recipeRepository.getRecipe(recipeId).fold(
                onSuccess = { recipe ->
                    _uiState.value = UiState.Content(recipe.toUi())
                },
                onFailure = {
                    // We can do more error handling here
                    Log.d(TAG, "Error fetching recipe", it)
                    _uiState.value = UiState.Error(UiError.UNSPECIFIED, recipeTitle)
                }
            )
        }
    }

    fun delete() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading(recipeTitle)
            recipeRepository.deleteRecipe(recipeId)
            _uiEvent.emit(UiEvent.RECIPE_DELETED)
            coordinator.reloadOverview()
        }
    }

    sealed class UiState(open val recipeName: String) {
        data class Loading(override val recipeName: String) : UiState(recipeName)
        data class Content(val recipeDetail: RecipeDetail) : UiState(recipeDetail.title)
        data class Error(val type: UiError, override val recipeName: String) : UiState(recipeName)
    }

    enum class UiError {
        UNSPECIFIED
    }

    enum class UiEvent {
        RECIPE_DELETED
    }
}

private fun Recipe.toUi() = RecipeDetail(
    title = title,
    subtitle = subtitle,
    ingredients = ingredients.map(Ingredient::toUi),
    steps = steps,
    recipeImage = RecipeImage.External(imageUrl),
)

private fun Ingredient.toUi() =
    UiIngredient(name, DecimalFormat("###.##").format(quantity), unit)