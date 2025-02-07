package com.example.recipes.fragments.addRecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipes.databinding.FragmentRecipesBinding
import com.example.recipes.room.AppDatabase
import com.example.recipes.room.RecipeDao
import com.example.recipes.room.RvAdapterRoomRecipes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipeDao: RecipeDao
    private lateinit var rvAdapterRoomRecipes: RvAdapterRoomRecipes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        // Initialize Room database
        binding.imageViewBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        val db =
            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "recipe_db").build()
        recipeDao = db.recipeDao()
        fetchRecipes()


        return binding.root
    }

    private fun fetchRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes = recipeDao.getAllRecipes()
            withContext(Dispatchers.Main) {
                if (recipes.isNotEmpty()) {
                    rvAdapterRoomRecipes =
                        RvAdapterRoomRecipes(recipeList = recipes, context = requireContext())
                    binding.rvRecipe.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    binding.rvRecipe.adapter = rvAdapterRoomRecipes
                } else {
                    binding.rvRecipe.visibility = View.GONE
                }
            }
        }
    }
}