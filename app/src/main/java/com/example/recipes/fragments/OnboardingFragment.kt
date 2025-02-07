package com.example.recipes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentOnboardingBinding
import com.google.firebase.auth.FirebaseAuth

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        click()
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    //Check if the user Logged in.
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            findNavController().navigate(R.id.action_onboardingFragment_to_discoverFragment)
        }
    }

    private fun click() {
        binding.onBoardingTvLogin.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                findNavController().navigate(R.id.action_onboardingFragment_to_discoverFragment)
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }
        binding.onBoardingTvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_createAccountFragment)
        }
    }
}