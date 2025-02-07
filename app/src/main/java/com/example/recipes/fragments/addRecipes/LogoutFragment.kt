package com.example.recipes.fragments.addRecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentLogoutBinding
import com.google.firebase.auth.FirebaseAuth


class LogoutFragment : Fragment() {
    private lateinit var binding:FragmentLogoutBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBinding.inflate(inflater,container,false)
        logoutDialog()
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(R.drawable.baseline_logout_24)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            auth.signOut()
//            navController!!.popBackStack(R.id.loginFragment, true)
            findNavController().navigate(R.id.action_logoutFragment_to_loginFragment)
        }
        //performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(requireContext(), "operation cancel", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.discoverFragment)
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(requireContext(), "No Logout", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.discoverFragment)
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}