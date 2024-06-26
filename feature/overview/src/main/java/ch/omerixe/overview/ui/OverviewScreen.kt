package ch.omerixe.overview.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.ui.ErrorBox
import ch.omerixe.ui.Loading
import ch.omerixe.ui.R
import ch.omerixe.ui.RecipeImage

@Composable
internal fun OverviewScreen(
    uiState: OverviewViewModel.UiState,
    onNavigateToRecipeDetail: (recipeId: String, recipeTitle: String) -> Unit
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
    onNavigateToRecipeDetail: (recipeId: String, recipeTitle: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        header {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.height(50.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        if (uiState.error != null) {
            item(span = { GridItemSpan(this.maxLineSpan) }) {
                ErrorBox(message = uiState.error.message())
            }
        }
        items(uiState.recipes) { recipe ->
            RecipeCard(recipeOverview = recipe) { recipeId ->
                Log.d("OverviewScreen", "Recipe clicked: $recipeId")
                onNavigateToRecipeDetail(
                    recipeId,
                    uiState.recipes.first { it.id == recipeId }.title
                )
            }
        }
    }
}

@Composable
private fun OverviewViewModel.UiError.message(): String {
    return when (this) {
        OverviewViewModel.UiError.UNSPECIFIED -> stringResource(R.string.overview_error_unspecified)
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
                        "1", "Test Recipe 1", RecipeImage.Internal(R.drawable.banana)
                    ),
                    RecipeOverview(
                        "2", "This is a very good recipe", RecipeImage.Internal(R.drawable.banana)
                    ),
                )
            )
        ) { _, _ -> }
    }
}


@Preview
@Composable
private fun ErrorPreview() {
    OverviewScreen(
        OverviewViewModel.UiState.Content(
            listOf(
                RecipeOverview(
                    "1", "Test Recipe 1", RecipeImage.Internal(R.drawable.banana)
                ),
                RecipeOverview(
                    "2", "This is a very good recipe", RecipeImage.Internal(R.drawable.banana)
                ),
            ), OverviewViewModel.UiError.UNSPECIFIED
        )
    ) { _, _ -> }
}


@Preview
@Composable
private fun LoadingPreview() {
    OverviewScreen(OverviewViewModel.UiState.Loading) { _, _ -> }
}

