package com.example.proyecto_a.retrofit

import com.example.proyecto_a.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal():Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query(value = "i")id:String) : Call<MealList>

}