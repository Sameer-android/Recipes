package com.example.recipes.room

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.databinding.ItemrecipesRoomBinding

class RvAdapterRoomRecipes(
    private val recipeList: List<RecipeEntity>,
    var context: Context
) : RecyclerView.Adapter<RvAdapterRoomRecipes.ViewHolder>() {
    inner class ViewHolder(var binding: ItemrecipesRoomBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =
            ItemrecipesRoomBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]

        // Load image using Glide from UR
        Glide.with(context)
            .load(Uri.parse(recipe.imageUri))
            .into(holder.binding.ivtRecipeRoom)


        // Set recipe name and description
        holder.binding.tvRecipeNameRoom.text = recipe.name
        holder.binding.tvRecipeDescription.text = recipe.description
    }
}