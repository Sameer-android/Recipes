package com.example.recipes.objects

import com.example.recipes.R
import com.example.recipes.models.DiscoverRecipesViewModel

object Constant {
    private lateinit var datalistRecipe: ArrayList<DiscoverRecipesViewModel>
    fun getDataDiscoverRecipe(): ArrayList<DiscoverRecipesViewModel> {

        datalistRecipe = ArrayList<DiscoverRecipesViewModel>()
        datalistRecipe.add(
            DiscoverRecipesViewModel(
                R.drawable.discoverrecipeimage, R.drawable.saveimage, "Morning Pancakes",
                R.drawable.imagepeopleicon, "Wade Warren"
            )
        )
        datalistRecipe.add(
            DiscoverRecipesViewModel(
                R.drawable.image_second_recipe, R.drawable.saveimage, "Pumpkin & Fish",
                R.drawable.icon_second_recipe, "Theresa Webb"
            )
        )
        datalistRecipe.add(
            DiscoverRecipesViewModel(
                R.drawable.image_third_recipe, R.drawable.saveimage, "Chaat Tikki (Chole...",
                R.drawable.icon_third_recipe, "Bessie Cooper"
            )
        )
        datalistRecipe.add(
            DiscoverRecipesViewModel(
                R.drawable.image_four_recipe, R.drawable.save_confirm_icon, "Gun Powder Recip...",
                R.drawable.icon_four_recipe, "Jerome Bell"
            )
        )
        return datalistRecipe
    }
}