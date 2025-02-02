package com.example.recipes.fragments.walkthrough

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentWalkthroughThreeBinding

class WalkthroughThreeFragment : Fragment() {
    private lateinit var binding:FragmentWalkthroughThreeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWalkthroughThreeBinding.inflate(inflater,container,false)
        back(binding.root)
        stringDiffColor()
        gotoDiscoverFragment()



        return binding.root
    }

    private fun gotoDiscoverFragment() {
        binding.imageViewNext.setOnClickListener {
            findNavController().navigate(R.id.action_walkthroughThreeFragment_to_discoverFragment)
        }
    }

    private fun back(view: View) {
        binding.imageViewBackArrow.setOnClickListener {
            view.findNavController().navigateUp()
        }
    }

    private fun stringDiffColor() {
        val text = "A Simple App for Everyone"
        val spannableString = SpannableString(text)

        val userTermService = text.indexOf("Everyone")
        val userNoticeEnd = userTermService + "Everyone".length

        val textColor = Color.parseColor("#E85353")
        spannableString.setSpan(ForegroundColorSpan(textColor), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRecordVoiceWalkthrough.text = spannableString
    }


}