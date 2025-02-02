package com.example.recipes.fragments.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private var mIsShowPass = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        click()
        stringDiffColor()
        binding.showPassword.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
        loginAcc()




        return binding.root
    }

    private fun click() {
        binding.tvForgotPass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
    }

    private fun showPassword(isShow:Boolean){
        if (isShow){
            binding.etPasswordLogin.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.baseline_visibility_off_24)
        } else{
            binding.etPasswordLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.show_password_eye)
        }
        binding.etPasswordLogin.setSelection(binding.etPasswordLogin.text.toString().length)
    }


    private fun stringDiffColor() {
        val text = "Don’t have an account with us? Signup"
        val spannableString = SpannableString(text)

        val userTermService = text.indexOf("Signup")
        val userNoticeEnd = userTermService + "Signup".length

        val textColor = Color.parseColor("#E85353")
        spannableString.setSpan(ForegroundColorSpan(textColor), userTermService, userNoticeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvDontHaveAccLogin.text = spannableString
    }

    private fun loginAcc() {
        binding.tvLogin.setOnClickListener {
            val userName = binding.etUserLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            if (userName.isEmpty()|| password.isEmpty()){
                Toast.makeText(requireContext(),"Fill All Details", Toast.LENGTH_LONG).show()
            }
            else{
                findNavController().navigate(R.id.action_loginFragment_to_discoverFragment)
            }
        }
    }

}