package com.example.recipes.objects

import com.example.recipes.R
import com.example.recipes.models.RecipesViewModel

object Constantdata {
    private lateinit var datalist: ArrayList<RecipesViewModel>
    fun getData(): ArrayList<RecipesViewModel> {
        datalist = ArrayList<RecipesViewModel>()
        datalist.add(RecipesViewModel(R.drawable.itemrecipeslogo, "All Recipes"))
        datalist.add(RecipesViewModel(R.drawable.recipeslogosecond, "BreakFast"))
        datalist.add(RecipesViewModel(R.drawable.recipeslogothird, "Lunch"))
        datalist.add(RecipesViewModel(R.drawable.recipeslogofour, "Dinner"))
        datalist.add(RecipesViewModel(R.drawable.itemrecipeslogo, "All Recipes"))
        datalist.add(RecipesViewModel(R.drawable.recipeslogosecond, "BreakFast"))
        return datalist
    }
}