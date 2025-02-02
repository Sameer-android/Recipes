package com.example.recipes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemBreakfastRecipeBinding
import com.example.recipes.models.BreakfastViewModel

class RvAdapterBreakfast(
    var dataListBreakfast: ArrayList<BreakfastViewModel>,
    var context: Context
) : RecyclerView.Adapter<RvAdapterBreakfast.ViewHolder>() {
    inner class ViewHolder(var binding: ItemBreakfastRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =
            ItemBreakfastRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataListBreakfast.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = dataListBreakfast.get(position)
        holder.binding.ivBreakfastRecipes.setImageResource(items.foodImage)
        holder.binding.tvRecipeDiscriptionBreakfast.text = items.foodText
        holder.binding.iconpeopleBreakfast.setImageResource(items.peopleIcon)
        holder.binding.tvnameimageBreakfast.text = items.iconText
    }
}