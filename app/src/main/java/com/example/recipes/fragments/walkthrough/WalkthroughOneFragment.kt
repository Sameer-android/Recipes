package com.example.recipes.fragments.walkthrough

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentWalkthroughOneBinding


class WalkthroughOneFragment : Fragment() {
    private lateinit var binding: FragmentWalkthroughOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWalkthroughOneBinding.inflate(inflater,container,false)
        click()
        stringDiffColor()
        return binding.root
    }

    private fun stringDiffColor() {
        val text = "Record Recipes with Your Voice"
        val spannableString = SpannableString(text)

        val userTermService = text.indexOf("Voice")
        val userNoticeEnd = userTermService + "Voice".length

        val textColor = Color.parseColor("#E85353")
        spannableString.setSpan(ForegroundColorSpan(textColor), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRecordVoiceWalkthrough.text = spannableString
    }

    private fun click() {
        binding.imageViewNext.setOnClickListener {
            findNavController().navigate(R.id.action_walkthroughOneFragment_to_walkthoroughTwoFragment)
        }
    }
}