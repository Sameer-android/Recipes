package com.example.recipes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemSaveRecipesBinding
import com.example.recipes.models.SaveRecipeViewModel

class RvAdapterSave(var dataListSaveRecipe: ArrayList<SaveRecipeViewModel>, var context: Context) :
    RecyclerView.Adapter<RvAdapterSave.ViewHolder>() {
    inner class ViewHolder(var binding: ItemSaveRecipesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemSaveRecipesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataListSaveRecipe.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = dataListSaveRecipe.get(position)
        holder.binding.ivSaveRecipes.setImageResource(items.foodImage)
        holder.binding.tvSaveRecipeDiscription.text = items.foodText
        holder.binding.iconpeopleSaveRecipe.setImageResource(items.peopleIcon)
        holder.binding.tviconNameSaveRecipe.text = items.iconText
    }
}