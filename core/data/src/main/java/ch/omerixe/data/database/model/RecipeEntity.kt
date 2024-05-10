package ch.omerixe.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val ingredients: List<DatabaseIngredient>,
    val steps: List<String>,
    val imageUrl: String,
    val version: Int
)

@Serializable
data class DatabaseIngredient(
    val name: String,
    val quantity: Double,
    val unit: String
)