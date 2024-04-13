package ch.omerixe.overview.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.ui.Loading
import ch.omerixe.ui.R
import ch.omerixe.ui.RecipeImage

@Composable
internal fun OverviewScreen(
    uiState: OverviewViewModel.UiState,
    onNavigateToRecipeDetail: (recipeId: String) -> Unit
) {
    when (uiState) {
        is OverviewViewModel.UiState.Loading -> {
            Loading()
        }
        is OverviewViewModel.UiState.Content -> {
            RecipeList(uiState, onNavigateToRecipeDetail)
        }
    }
}

@Composable
private fun RecipeList(
    uiState: OverviewViewModel.UiState.Content,
    onNavigateToRecipeDetail: (recipeId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        header {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(uiState.recipes) { recipe ->
            RecipeCard(recipeOverview = recipe) { recipeId ->
                Log.d("OverviewScreen", "Recipe clicked: $recipeId")
                onNavigateToRecipeDetail(recipeId)
            }
        }
    }
}

private fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun PreviewOverviewScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
        OverviewScreen(
            OverviewViewModel.UiState.Content(
                recipes = listOf(
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
                )
            )
        ) {}
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    OverviewScreen(OverviewViewModel.UiState.Loading) {}
}

