package com.example.proyecto_a.videoModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_a.db.MealDataBase

class MealViewModelFactory(
    val mealDataBase: MealDataBase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealDataBase) as T
    }

}