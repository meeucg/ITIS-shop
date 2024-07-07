package com.example.itis_shop

import FavoriteFiles.CustomDialogFragment
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.itis_shop.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController

        controller?.let { navController ->
            binding?.bottomNavigation?.setupWithNavController(navController)
            navController.addOnDestinationChangedListener {_, destination, _ ->
                handleBottomNavigationViewVisibility(destination)
            }
        }

    }

    private fun handleBottomNavigationViewVisibility(destination: NavDestination){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        val showBottomNav = when (destination.id) {
            else -> true
        }

        bottomNavigationView.visibility = if (showBottomNav) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}