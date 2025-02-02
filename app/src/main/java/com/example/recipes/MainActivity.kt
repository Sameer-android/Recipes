package com.example.recipes

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.recipes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.textColor)
        onBackPress()
        onTouchListenerRootLayout()

    }

    private fun onTouchListenerRootLayout() {
        binding.main.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            false
        }
    }

    /*private fun setUpBottomNavigation() {
        bottomNavigationView = binding.bottomBar
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        navController = navHostFragment?.navController
    }*/

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController != null && navController?.currentDestination?.id == R.id.onboardingFragment) {
                    finishAffinity()
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        })
    }
    /*fun showBottomBar(show: Boolean) {
        binding.bottomBar.visibility = if (show) View.VISIBLE else View.GONE
    }*/


}