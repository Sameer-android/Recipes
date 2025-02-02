package com.example.recipes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemdiscoverrecipesBinding
import com.example.recipes.models.DiscoverRecipesViewModel

class RvAdapterDiscover(
    var datalistRecipe: ArrayList<DiscoverRecipesViewModel>,
    var context: Context
) : RecyclerView.Adapter<RvAdapterDiscover.ViewHolder>() {
    inner class ViewHolder(var binding: ItemdiscoverrecipesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =
            ItemdiscoverrecipesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalistRecipe.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = datalistRecipe.get(position)
        holder.binding.ivDiscoverRecipes.setImageResource(items.foodImage)
        holder.binding.iconSaveRecipe.setImageResource(items.safeIcon)
        holder.binding.tvRecipeDiscription.text = items.foodText
        holder.binding.iconpeople.setImageResource(items.peopleIcon)
        holder.binding.tvnameimage.text = items.iconName
    }
}