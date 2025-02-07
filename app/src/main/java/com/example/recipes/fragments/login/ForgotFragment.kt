package com.example.recipes.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentForgotBinding

class ForgotFragment : Fragment() {
    private lateinit var binding: FragmentForgotBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotBinding.inflate(inflater,container,false)
        click(binding.root)
        return binding.root
    }


    private fun click(view: View) {
        binding.imageViewBackArrow.setOnClickListener {
            view.findNavController().navigateUp()
        }
    }
}