package ch.omerixe.omnirecipe.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import ch.omerixe.omnirecipe.shared.ui.theme.OmniRecipeTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(onNavigateUp: () -> Unit) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_up_description)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
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
}

@Preview
@Composable
fun RecipeDetailScreenPreview() {
    OmniRecipeTheme {
        RecipeDetailScreen({})
    }
}