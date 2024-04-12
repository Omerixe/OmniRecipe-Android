package ch.omerixe.omnirecipe.overview.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.omnirecipe.R
import ch.omerixe.omnirecipe.shared.ui.RecipeImage
import ch.omerixe.omnirecipe.shared.ui.theme.OmniRecipeTheme

@Composable
fun OverviewScreen() {
    val recipes = listOf(
        RecipeOverview(
            "1",
            "Test Recipe 1",
            "Subtitle 1",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "2",
            "Test Recipe 2",
            "Subtitle 2",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "3",
            "Test Recipe 3",
            "Subtitle 3",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "4",
            "Test Recipe 4",
            "Subtitle 4",
            RecipeImage.Internal(R.drawable.banana)
        ),
        RecipeOverview(
            "5",
            "Test Recipe 5",
            "Subtitle 5",
            RecipeImage.Internal(R.drawable.banana)
        ),
    )

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
        items(recipes) { recipe ->
            RecipeCard(recipeOverview = recipe) { recipeId ->
                Log.d("OverviewScreen", "Recipe clicked: $recipeId")
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
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun PreviewOverviewScreen() {
    OmniRecipeTheme {
        OverviewScreen()
    }
}



