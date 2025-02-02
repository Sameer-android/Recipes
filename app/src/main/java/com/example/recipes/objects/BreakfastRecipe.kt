package com.example.recipes.objects

import com.example.recipes.R
import com.example.recipes.models.BreakfastViewModel

object BreakfastRecipe {
    private lateinit var datalistBreakfast:ArrayList<BreakfastViewModel>
    fun getData():ArrayList<BreakfastViewModel>{

        datalistBreakfast = ArrayList<BreakfastViewModel>()
        datalistBreakfast.add(BreakfastViewModel(
            R.drawable.breakfast_image,"Gun Powder Recipe (Paruppu Podi)",
            R.drawable.breakfast_icon_people,"Marvin McKinney"))
        datalistBreakfast.add(BreakfastViewModel(R.drawable.breakfast_image,"Gun Powder Recipe (Paruppu Podi)",
            R.drawable.breakfast_second_icon,"Dianne Russell"))
        return datalistBreakfast
    }
}