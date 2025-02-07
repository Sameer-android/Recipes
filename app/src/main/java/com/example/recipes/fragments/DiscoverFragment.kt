package com.example.recipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.MainActivity
import com.example.recipes.R
import com.example.recipes.adapters.RvAdapterBreakfast
import com.example.recipes.adapters.RvAdapterDiscover
import com.example.recipes.adapters.RvAdapterRecipes
import com.example.recipes.adapters.RvAdapterSave
import com.example.recipes.databinding.FragmentDiscoverBinding
import com.example.recipes.objects.BreakfastRecipe
import com.example.recipes.objects.Constant
import com.example.recipes.objects.Constantdata
import com.example.recipes.objects.SaveRecipe
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class DiscoverFragment : Fragment() {
    private lateinit var binding:FragmentDiscoverBinding
    private lateinit var rvAdapterRecipes: RvAdapterRecipes
    private lateinit var rvAdapterDiscover: RvAdapterDiscover
    private lateinit var rvAdapterBreakfast: RvAdapterBreakfast
    private lateinit var rvAdapterSave: RvAdapterSave
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()
        showRecipeRecyclerView()
        showDiscoverRecipe()
        showBreakfastRecipe()
        showSaveRecipe()
        /*bottomBar()*/

        return binding.root
    }

    private fun showRecipeRecyclerView() {
        rvAdapterRecipes = RvAdapterRecipes(Constantdata.getData(), requireActivity())
        binding.rvRecipes.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false,)
        binding.rvRecipes.adapter = rvAdapterRecipes
    }
    private fun showDiscoverRecipe() {
        rvAdapterDiscover = RvAdapterDiscover(Constant.getDataDiscoverRecipe(), requireActivity())
        binding.rvDiscoverRecipe.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvDiscoverRecipe.adapter = rvAdapterDiscover
    }
    private fun showBreakfastRecipe() {
        rvAdapterBreakfast = RvAdapterBreakfast(BreakfastRecipe.getData(), requireActivity())
        binding.rvBreakfastRecipe.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBreakfastRecipe.adapter = rvAdapterBreakfast
    }

    private fun showSaveRecipe() {
        rvAdapterSave = RvAdapterSave(SaveRecipe.getData(), requireActivity())
        binding.rvSaveRecipe.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSaveRecipe.adapter = rvAdapterSave
    }


}