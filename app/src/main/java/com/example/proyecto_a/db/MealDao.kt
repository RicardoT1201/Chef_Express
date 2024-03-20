package com.example.proyecto_a.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_a.pojo.Meal

@Dao
abstract class MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsert(meal: Meal)

    @Delete
    abstract suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    abstract fun getAllMeals():LiveData<List<Meal>>


}