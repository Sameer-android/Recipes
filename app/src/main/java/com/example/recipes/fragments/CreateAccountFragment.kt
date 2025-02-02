package com.example.recipes.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private var mIsShowPass = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        binding.showPassword.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
        createAccount()
        stringDiffColor()


        return binding.root
    }

    private fun stringDiffColor() {
        val text = "I agree to the Terms of Service and Privacy Policy"
        val spannableString = SpannableString(text)
        val alreadyAccText = "Already have an account? Login"
        val spannableStringText = SpannableString(alreadyAccText)


        val userTermService = text.indexOf("Terms of Service")
        val userNoticeEnd = userTermService + "Terms of Service".length

        val privacyPolicyStart = text.indexOf("Privacy Policy")
        val privacyPolicyEnd = privacyPolicyStart + "Privacy Policy".length

        val alreadyAccStart = alreadyAccText.indexOf("Login")
        val alreadyAccEnd = alreadyAccStart + "Login".length

        val loginClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
            }
        }


        val textColor = Color.parseColor("#E85353")

        spannableStringText.setSpan(loginClick,alreadyAccStart,alreadyAccEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringText.setSpan(UnderlineSpan(),alreadyAccStart,alreadyAccEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringText.setSpan(ForegroundColorSpan(textColor),alreadyAccStart,alreadyAccEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        spannableString.setSpan(UnderlineSpan(), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(textColor), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(UnderlineSpan(), privacyPolicyStart, privacyPolicyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(textColor), privacyPolicyStart, privacyPolicyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTermCondition.text = spannableString
        binding.tvAlreadyAcc.text = spannableStringText
        binding.tvAlreadyAcc.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun createAccount() {
        binding.tvNextCreateAcc.setOnClickListener {
            val name = binding.etNameCreateAcc.text.toString()
            val userName = binding.etUserNameCreateAcc.text.toString()
            val email = binding.etEmailCreateAcc.text.toString()
            val password = binding.etPasswordCreateAcc.text.toString()
            val checkBox = binding.CheckBox.isChecked

            if (name.isEmpty() || userName.isEmpty()|| email.isEmpty()|| password.isEmpty() || !checkBox){
                Toast.makeText(requireContext(),"Fill All Details And Check", Toast.LENGTH_LONG).show()
            }
            else{
                findNavController().navigate(R.id.action_createAccountFragment_to_walkthroughOneFragment)
            }
        }
    }

    private fun showPassword(isShow:Boolean){
        if (isShow){
            binding.etPasswordCreateAcc.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.baseline_visibility_off_24)
        } else{
            binding.etPasswordCreateAcc.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.show_password_eye)
        }
        binding.etPasswordCreateAcc.setSelection(binding.etPasswordCreateAcc.text.toString().length)
    }
}