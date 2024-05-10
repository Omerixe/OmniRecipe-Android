package ch.omerixe.data.database

import androidx.room.TypeConverter
import ch.omerixe.data.database.model.DatabaseIngredient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Converters {

    @TypeConverter
    fun fromStringToIngredientsList(value: String): List<DatabaseIngredient> {
        return Json.decodeFromString<List<DatabaseIngredient>>(value)
    }

    @TypeConverter
    fun fromIngredientsListToString(value: List<DatabaseIngredient>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {
        return Json.decodeFromString<List<String>>(value)
    }

    @TypeConverter
    fun fromStringListToString(value: List<String>): String {
        return Json.encodeToString(value)
    }
}