package com.example.recipes.fragments

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentCreateAccountBinding
import com.example.recipes.fragments.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private var mIsShowPass = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
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

        spannableStringText.setSpan(
            loginClick,
            alreadyAccStart,
            alreadyAccEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringText.setSpan(
            UnderlineSpan(),
            alreadyAccStart,
            alreadyAccEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringText.setSpan(
            ForegroundColorSpan(textColor),
            alreadyAccStart,
            alreadyAccEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        spannableString.setSpan(
            UnderlineSpan(),
            userTermService,
            userNoticeEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(textColor),
            userTermService,
            userNoticeEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            UnderlineSpan(),
            privacyPolicyStart,
            privacyPolicyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(textColor),
            privacyPolicyStart,
            privacyPolicyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

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

            if (name.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty() && password.length<8 || !checkBox) {
                Toast.makeText(requireContext(), "Fill All Details And CheckBox", Toast.LENGTH_LONG)
                    .show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Activity()) { task ->
                        if (task.isSuccessful) {
                            addUserToDatabase(name, email, auth.currentUser?.uid!!)
                            Toast.makeText(
                                requireContext(),
                                "Registration Successful",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_createAccountFragment_to_walkthroughOneFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Registration Failed:${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            binding.etPasswordCreateAcc.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.baseline_visibility_off_24)
        } else {
            binding.etPasswordCreateAcc.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.showPassword.setImageResource(R.drawable.show_password_eye)
        }
        binding.etPasswordCreateAcc.setSelection(binding.etPasswordCreateAcc.text.toString().length)
    }
}