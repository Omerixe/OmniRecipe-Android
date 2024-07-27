package ch.omerixe.recipedetail.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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

private val imageHeight = 250.dp

@Composable
internal fun RecipeDetailScreen(
    uiState: RecipeDetailViewModel.UiState,
    onNavigateUp: () -> Unit
) {
    val scrollState = rememberScrollState()
    val toolbarAlpha = alphaFromScrollState(scrollState)
    val surfaceColor = MaterialTheme.colorScheme.surface.copy(alpha = toolbarAlpha)

    Scaffold() { padding ->
        when (uiState) {
            is RecipeDetailViewModel.UiState.Loading -> {
                Column {
                    TopAppBar(
                        toolbarAlpha,
                        { Text(uiState.recipeName) },
                        surfaceColor,
                        onNavigateUp,
                        padding
                    )
                    Loading()
                }
            }

            is RecipeDetailViewModel.UiState.Content -> {
                RecipeComponent(uiState, scrollState)
                val title = @Composable {
                    if (toolbarAlpha == 1f) {
                        Text(uiState.recipeName)
                    }
                }
                TopAppBar(toolbarAlpha, title, surfaceColor, onNavigateUp, padding)
            }

            is RecipeDetailViewModel.UiState.Error -> {
                Column {
                    TopAppBar(
                        toolbarAlpha,
                        { Text(uiState.recipeName) },
                        surfaceColor,
                        onNavigateUp,
                        padding
                    )
                    ErrorBox(message = uiState.type.message(), modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBar(
    toolbarAlpha: Float,
    title: @Composable () -> Unit,
    surfaceColor: Color,
    onNavigateUp: () -> Unit,
    padding: PaddingValues
) {
    TopAppBar(
        title = title,
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = surfaceColor),
        navigationIcon = {
            IconButton(
                onClick = onNavigateUp,
                colors = IconButtonDefaults.iconButtonColors().copy(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                        .copy(alpha = 1 - toolbarAlpha),
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_up_description)
                )
            }
        },
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun alphaFromScrollState(scrollState: ScrollState): Float {
    val percentage = scrollState.value / LocalDensity.current.run { imageHeight.toPx() }
    return if (percentage < 1) percentage else 1f
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
    scrollState: ScrollState
) {
    val recipeDetail = uiState.recipeDetail
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        AsyncImage(
            model = recipeDetail.recipeImage.imageModel(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.banana),
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = recipeDetail.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            )
            Text(
                text = recipeDetail.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            IngredientComponent(recipeDetail)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail_steps_heading),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            )
            recipeDetail.steps.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                )
            }
        }
    }
}

@Composable
private fun IngredientComponent(recipeDetail: RecipeDetail) {
    Column(
        Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = stringResource(R.string.detail_ingredients_heading),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Column {
                recipeDetail.ingredients.forEach { ingredient ->
                    Text(
                        text = "${ingredient.quantity} ${ingredient.unit}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Spacer(modifier = Modifier.width(64.dp))

            Column {
                recipeDetail.ingredients.forEach { ingredient ->
                    Text(
                        text = ingredient.name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
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
                    UiIngredient("Bananas", "2", "pcs"),
                    UiIngredient("Honey", "1", "tbsp"),
                    UiIngredient("Milk", "1", "cup"),
                    UiIngredient("Ice", "1", "cup"),
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
        uiState = RecipeDetailViewModel.UiState.Error(
            RecipeDetailViewModel.UiError.UNSPECIFIED,
            "Recipe Title"
        )
    ) {}
}