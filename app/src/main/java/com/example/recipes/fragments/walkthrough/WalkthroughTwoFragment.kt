package com.example.recipes.fragments.walkthrough

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentWalkthoroughTwoBinding

class WalkthroughTwoFragment : Fragment() {
    private lateinit var binding: FragmentWalkthoroughTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWalkthoroughTwoBinding.inflate(inflater, container, false)
        click()
        stringDiffColor()
        back(binding.root)
        return binding.root
    }

    private fun click() {
        binding.imageViewNext.setOnClickListener {
            findNavController().navigate(R.id.action_walkthoroughTwoFragment_to_walkthroughThreeFragment)
        }

    }
    private fun back(view: View) {
        binding.imageViewBackArrow.setOnClickListener {
            view.findNavController().navigateUp()
        }
    }

    private fun stringDiffColor() {
        val text = "Share Your Culinary Legacy"
        val spannableString = SpannableString(text)

        val userTermService = text.indexOf("Legacy")
        val userNoticeEnd = userTermService + "Legacy".length

        val textColor = Color.parseColor("#E85353")
        spannableString.setSpan(ForegroundColorSpan(textColor), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRecordVoiceWalkthrough.text = spannableString
    }
}