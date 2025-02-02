package com.example.recipes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemrecipesBinding
import com.example.recipes.models.RecipesViewModel

class RvAdapterRecipes(var datalist: ArrayList<RecipesViewModel>, var context: Context) :
    RecyclerView.Adapter<RvAdapterRecipes.MyViewHolder>() {
    inner class MyViewHolder(var binding: ItemrecipesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ItemrecipesBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = datalist[position]
        holder.binding.itemrecipeslogo.setImageResource(items.imageView)
        holder.binding.textRecipes.text = items.text
    }
}