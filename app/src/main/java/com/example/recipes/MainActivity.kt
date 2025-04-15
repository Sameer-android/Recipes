package com.example.recipes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.recipes.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigationView: FragmentContainerView
    private var navController: NavController? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.textColor)
        onBackPress()
        onTouchListenerRootLayout()
        setUpBottomNavigation()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchListenerRootLayout() {
        binding.main.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            false
        }
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(R.drawable.baseline_logout_24)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            auth.signOut()
//            navController!!.popBackStack(R.id.loginFragment, true)
            navController?.navigate(R.id.action_discoverFragment_to_loginFragment)
        }
        /*//performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(this, "operation cancel", Toast.LENGTH_SHORT).show()
        }*/
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(this, "No Logout", Toast.LENGTH_SHORT).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun setUpBottomNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        navController = navHostFragment.navController

//        binding.bottomBar.selectedItemId = R.id.discover


        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.discover -> {
                    navController!!.navigate(R.id.discoverFragment)
                    true
                }

                R.id.setting -> {
                    logoutDialog()
                    false

                }

                R.id.mainPlus -> {
                    navController!!.navigate(R.id.addRecipesFragment)
                    false

                }

                R.id.chat -> {
                    navController!!.navigate(R.id.usersFragment)
                    false
                }

                R.id.recipes -> {
                    navController!!.navigate(R.id.recipesFragment)
                    true

                }
            }
            false
        }

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            Handler(Looper.getMainLooper()).post {
                binding.bottomBar.visibility =
                    if ((destination.id == R.id.discoverFragment) ||(destination.id == R.id.logoutFragment)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }
    }

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                Log.e(
                    "current_destination_id===",
                    navController?.currentDestination?.label.toString()
                )
                if (navController != null && (navController?.currentDestination?.id == R.id.discoverFragment ||
                            navController?.currentDestination?.id == R.id.onboardingFragment || navController?.currentDestination?.id == R.id.loginFragment )
                ) {
                    finish()
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        })
    }


}