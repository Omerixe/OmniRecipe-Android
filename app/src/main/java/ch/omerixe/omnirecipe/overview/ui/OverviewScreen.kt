package ch.omerixe.omnirecipe.overview.ui

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.omnirecipe.R
import ch.omerixe.omnirecipe.shared.ui.RecipeImage
import ch.omerixe.omnirecipe.theme.OmniRecipeTheme

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
        )
    )

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        item {
            Row {
                Text(
                    text = stringResource(R.string.overview_heading),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        items(recipes) { recipe ->
            RecipeCard(recipeOverview = recipe) { recipeId ->
                Log.d("OverviewScreen", "Recipe clicked: $recipeId")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Preview
@Composable
fun PreviewOverviewScreen() {
    OmniRecipeTheme {
        OverviewScreen()
    }
}

