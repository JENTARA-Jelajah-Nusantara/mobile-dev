package com.example.capstone.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.data.pref.UserPreference

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private lateinit var userPreferences: UserPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreference(requireContext())

        view.postDelayed({
            if (userPreferences.isOnboardingComplete()) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
            }
        }, SPLASH_TIME_OUT)
    }

    companion object {
        const val SPLASH_TIME_OUT = 3000L
    }
}