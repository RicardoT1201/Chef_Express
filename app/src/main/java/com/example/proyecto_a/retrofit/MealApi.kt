package com.example.proyecto_a.retrofit

import com.example.proyecto_a.pojo.CategoryList
import com.example.proyecto_a.pojo.MealsByCategoryList
import com.example.proyecto_a.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("categories.php")
    fun getCategories () : Call<CategoryList>

    @GET ("random.php")
    fun getRandomMeal() : Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c")categoryName:String) : Call<MealsByCategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c")categoryName: String) : Call<MealsByCategoryList>

    @GET("search.php")
    fun searchMeals(@Query("s")searchQuery:String) : Call<MealList>

}
