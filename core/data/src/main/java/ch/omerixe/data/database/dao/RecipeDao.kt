package ch.omerixe.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ch.omerixe.data.database.model.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id = :id LIMIT 1")
    fun findById(id: String): RecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg recipes: RecipeEntity)

    @Delete()
    fun delete(recipe: RecipeEntity)


}