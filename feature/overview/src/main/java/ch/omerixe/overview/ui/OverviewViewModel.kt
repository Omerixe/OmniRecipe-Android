package ch.omerixe.overview.ui

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

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val recipeSummaryRepository: RecipeSummaryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val recipeSummaries = recipeSummaryRepository.getRecipeSummaries()
            _uiState.value = UiState.Content(recipeSummaries.map { it.toUi() })
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Content(val recipes: List<RecipeOverview>) : UiState()
    }
}

private fun RecipeSummary.toUi(): RecipeOverview {
    return RecipeOverview(
        id = id,
        title = title,
        recipeImage = RecipeImage.External(imageUrl)
    )
}