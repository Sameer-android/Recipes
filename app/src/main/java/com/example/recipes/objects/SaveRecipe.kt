package com.example.recipes.objects

import com.example.recipes.R
import com.example.recipes.models.SaveRecipeViewModel

object SaveRecipe {
    private lateinit var datalistSaveRecipe: ArrayList<SaveRecipeViewModel>
    fun getData(): ArrayList<SaveRecipeViewModel> {

        datalistSaveRecipe = ArrayList<SaveRecipeViewModel>()
        datalistSaveRecipe.add(
            SaveRecipeViewModel(
                R.drawable.save_recipe_image, "Chaat Tikki (Chole...",
                R.drawable.save_recipe_icon, "Albert Flores"
            )
        )
        datalistSaveRecipe.add(
            SaveRecipeViewModel(
                R.drawable.save_recipe_second, "Gun Powder Recip...",
                R.drawable.save_recipe_icon_second, "Floyd Miles"
            )
        )
        datalistSaveRecipe.add(
            SaveRecipeViewModel(
                R.drawable.save_recipe_third_image, "Chaat Tikki (Chole",
                R.drawable.save_recipe_icon_third, "Jenny Wilson"
            )
        )
        datalistSaveRecipe.add(
            SaveRecipeViewModel(
                R.drawable.save_recipe_image, "Chaat Tikki (Chole...",
                R.drawable.save_recipe_icon, "Albert Flores"
            )
        )
        return datalistSaveRecipe
    }
}