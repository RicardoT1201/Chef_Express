package com.example.proyecto_a.videoModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto_a.db.MealDataBase
import com.example.proyecto_a.pojo.Category
import com.example.proyecto_a.pojo.CategoryList
import com.example.proyecto_a.pojo.MealsByCategoryList
import com.example.proyecto_a.pojo.MealsByCategory
import com.example.proyecto_a.pojo.Meal
import com.example.proyecto_a.pojo.MealList
import com.example.proyecto_a.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val mealDataBase: MealDataBase
):ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var favoritesMealsLiveData = mealDataBase.mealDao().getAllMeals()
    private val searchedMealsLiveData = MutableLiveData<List<Meal>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems(){

        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {

                if(response.body() !=null){
                    popularItemsLiveData.value = response.body()!!.meals
                }

            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {

                Log.d("HomeFragment",t.message.toString())

            }

        })

    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                    }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {

                Log.e("HomeViewModel",t.message.toString())


            }

        })
    }



    fun searchMeals(searchQuery:String) = RetrofitInstance.api.searchMeals(searchQuery).enqueue(
        object : Callback <MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                val mealsList = response.body()?.meals
                mealsList?.let {
                    searchedMealsLiveData.postValue(it)

                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("HomeViewModel",t.message.toString())


            }
        }
    )
    fun observeSearchedMealsLivedata() : LiveData<List<Meal>> = searchedMealsLiveData
    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }

    fun observePopularItemsLiveData():LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }

    fun observeCategoriesLiveData():LiveData<List<Category>>{
        return categoriesLiveData

    }

    fun observeFavoritesMealsLiveData():LiveData<List<Meal>>{
        return favoritesMealsLiveData
    }

}