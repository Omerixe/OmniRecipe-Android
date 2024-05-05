package ch.omerixe.overview.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.omerixe.data.domain.RecipeSummaryRepository
import ch.omerixe.data.model.RecipeSummary
import ch.omerixe.ui.RecipeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "OverviewViewModel"

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val recipeSummaryRepository: RecipeSummaryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            recipeSummaryRepository.getRecipeSummaries().fold(
                onSuccess = { summaries ->
                    _uiState.value = UiState.Content(summaries.map { it.toUi() })
                },
                onFailure = {
                    // We can do more error handling here
                    Log.d(TAG, "Error fetching recipes", it)
                    _uiState.value = UiState.Content(emptyList(), UiError.UNSPECIFIED)
                }
            )
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipes: List<RecipeOverview>, val error: UiError? = null) :
            UiState()
    }

    enum class UiError {
        UNSPECIFIED
    }
}

private fun RecipeSummary.toUi(): RecipeOverview {
    return RecipeOverview(
        id = id,
        title = title,
        recipeImage = RecipeImage.External(imageUrl)
    )
}