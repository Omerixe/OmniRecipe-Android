package ch.omerixe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ch.omerixe.data.database.dao.RecipeDao
import ch.omerixe.data.database.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class OmniDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

}