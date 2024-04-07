package ch.omerixe.omnirecipe.overview.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ch.omerixe.omnirecipe.R
import ch.omerixe.omnirecipe.theme.OmniRecipeTheme
import coil.compose.AsyncImage

@Composable
fun RecipeCard(
    title: String,
    subtitle: String,
    recipeImage: RecipeImage,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (image, text) = createRefs()

            AsyncImage(
                model = recipeImage.imageModel(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.percent(0.25f)
                    height = Dimension.fillToConstraints
                },
                placeholder = painterResource(id = R.drawable.banana),
            )

            Column(modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                start.linkTo(image.end, margin = 8.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 2
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }

        }
    }
}

private fun RecipeImage.imageModel(): Any {
    return when (this) {
        is RecipeImage.External -> url
        is RecipeImage.Internal -> resId
    }
}

@Preview
@Composable
fun PreviewRecipeCard() {
    OmniRecipeTheme {
        RecipeCard(
            title = "Banana Bread",
            subtitle = "This is a delicious banana bread recipe",
            recipeImage = RecipeImage.Internal(R.drawable.banana),
            modifier = Modifier.padding(
                start = 16.dp,
                top = 0.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        )
    }
}