package com.example.capstone.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.capstone.R
import com.example.capstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private val navHostController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.navView.setupWithNavController(navHostController)

        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashScreenFragment || destination.id == R.id.onBoardingFragment || destination.id == R.id.loginFragment || destination.id == R.id.registerFragment)
            {
                binding.navView.visibility = View.GONE
            } else {
                binding.navView.visibility = View.VISIBLE
            }
        }
    }
}