package ch.omerixe.recipedetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import ch.omerixe.ui.ErrorBox
import ch.omerixe.ui.Loading
import ch.omerixe.ui.R
import ch.omerixe.ui.RecipeImage
import ch.omerixe.ui.imageModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeDetailScreen(
    uiState: RecipeDetailViewModel.UiState,
    onNavigateUp: () -> Unit
) {
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
        when (uiState) {
            is RecipeDetailViewModel.UiState.Loading -> {
                Loading()
            }

            is RecipeDetailViewModel.UiState.Content -> {
                RecipeComponent(uiState, padding)
            }

            is RecipeDetailViewModel.UiState.Error -> {
                ErrorBox(message = uiState.type.message(), modifier = Modifier.padding(padding))

            }
        }
    }
}

@Composable
private fun RecipeDetailViewModel.UiError.message(): String {
    return when (this) {
        RecipeDetailViewModel.UiError.UNSPECIFIED -> stringResource(R.string.detail_error_unspecified)
    }
}

@Composable
private fun RecipeComponent(
    uiState: RecipeDetailViewModel.UiState.Content,
    padding: PaddingValues
) {
    val recipeDetail = uiState.recipeDetail
    Column(
        modifier = Modifier
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
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
private fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        uiState = RecipeDetailViewModel.UiState.Content(
            recipeDetail = RecipeDetail(
                title = "Banana Smoothie",
                subtitle = "A delicious and refreshing smoothie",
                recipeImage = RecipeImage.Internal(R.drawable.banana),
                ingredients = listOf(
                    Ingredient("Bananas", "2", "pcs"),
                    Ingredient("Honey", "1", "tbsp"),
                    Ingredient("Milk", "1", "cup"),
                    Ingredient("Ice", "1", "cup"),
                ),
                steps = listOf(
                    "Peel the bananas",
                    "Add the bananas to the blender",
                    "Add the honey, milk, and ice to the blender",
                    "Blend until smooth",
                ),
            )
        )
    ) {}
}

@Preview
@Composable
fun RecipeDetailScreenErrorPreview() {
    RecipeDetailScreen(
        uiState = RecipeDetailViewModel.UiState.Error(RecipeDetailViewModel.UiError.UNSPECIFIED)
    ) {}
}