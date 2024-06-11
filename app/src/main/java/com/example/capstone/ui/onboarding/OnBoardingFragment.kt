package com.example.capstone.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.capstone.R
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.databinding.FragmentOnBoardingBinding
import com.example.capstone.model.OnBoarding
import com.example.capstone.ui.adapter.OnBoardingViewPagerAdapter

class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var userPreferences: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreference(requireContext())

        val onBoardingData: MutableList<OnBoarding> = ArrayList()
        onBoardingData.add(OnBoarding("Selamat Datang di Jentara!", "Temukan destinasi menakjubkan, panduan perjalanan lengkap, dan rekomendasi aktivitas yang tak terlupakan.",
            R.drawable.onboarding1
        ))
        onBoardingData.add(OnBoarding("Temukan Destinasi Impian Anda", "Dari pantai yang menawan hingga pegunungan yang megah, kami menyediakan informasi lengkap dan tips perjalanan untuk setiap lokasi.",
            R.drawable.onboarding2
        ))
        onBoardingData.add(OnBoarding("Rencanakan dan Nikmati Perjalanan Anda", "Mulailah petualangan Anda bersama Jentara dan temukan keajaiban Indonesia yang menanti untuk dijelajahi.",
            R.drawable.onboarding3
        ))

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(requireContext(), onBoardingData)
        binding.screenPager.adapter = onBoardingViewPagerAdapter

        val wormDotsIndicator = binding.wormDotsIndicator
        wormDotsIndicator.attachTo(binding.screenPager)

        var position = binding.screenPager.currentItem
        binding.btnNext.setOnClickListener {
            if (position < onBoardingData.size - 1) {
                position++
                binding.screenPager.currentItem = position
            }
        }

        binding.btnPrev.setOnClickListener {
            if (position > 0) {
                position--
                binding.screenPager.currentItem = position
            }
        }

        binding.screenPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == onBoardingData.size - 1) {
                    binding.btnLogin.visibility = View.VISIBLE
                    binding.btnInto.visibility = View.VISIBLE
                } else {
                    binding.btnLogin.visibility = View.GONE
                    binding.btnInto.visibility = View.GONE
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            userPreferences.setOnboardingComplete(true)
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

        binding.btnInto.setOnClickListener {
            userPreferences.setOnboardingComplete(true)
            findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
        }
    }
}