package ch.omerixe.overview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.omerixe.ui.R
import ch.omerixe.ui.RecipeImage
import ch.omerixe.ui.imageModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeCard(
    recipeOverview: RecipeOverview,
    modifier: Modifier = Modifier,
    action: (String) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = { action(recipeOverview.id) },
        modifier = modifier
    ) {
        Column {
            AsyncImage(
                model = recipeOverview.recipeImage.imageModel(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.banana),
            )
            Text(
                text = recipeOverview.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewRecipeCard() {
    RecipeCard(
        recipeOverview = RecipeOverview(
            "1",
            "Test Recipe 1",
            RecipeImage.Internal(R.drawable.banana)
        ),
        modifier = Modifier.padding(
            start = 16.dp,
            top = 0.dp,
            end = 16.dp,
            bottom = 16.dp
        )
    )
}