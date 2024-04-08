package ch.omerixe.omnirecipe.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.omnirecipe.R
import ch.omerixe.omnirecipe.shared.ui.RecipeImage
import ch.omerixe.omnirecipe.shared.ui.imageModel
import ch.omerixe.omnirecipe.theme.OmniRecipeTheme
import coil.compose.AsyncImage

@Composable
fun DetailScreen() {
    val recipeDetail = RecipeDetail(
        "Banana Smoothie",
        "A delicious and refreshing smoothie",
        RecipeImage.Internal(R.drawable.banana),
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

    Column {
        AsyncImage(
            model = recipeDetail.recipeImage.imageModel(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.banana),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recipeDetail.title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = recipeDetail.subtitle,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail_ingredients_heading),
                style = MaterialTheme.typography.titleMedium,
            )
            Row {
                Column {
                    recipeDetail.ingredients.forEach { ingredient ->
                        Text(
                            text = ingredient.quantity,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    recipeDetail.ingredients.forEach { ingredient ->
                        Text(
                            text = ingredient.unit,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    recipeDetail.ingredients.forEach { ingredient ->
                        Text(
                            text = ingredient.name,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail_steps_heading),
                style = MaterialTheme.typography.titleMedium,
            )
            recipeDetail.steps.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    OmniRecipeTheme {
        DetailScreen()
    }
}