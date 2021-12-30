package com.kemalurekli.fasttouch

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.Navigation
import com.kemalurekli.fasttouch.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundImg : ImageView = binding.splashLogo
        val sideAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.slide)
        backgroundImg.startAnimation(sideAnimation)
        Handler().postDelayed({
            Navigation.findNavController(requireView()).navigate(SplashFragmentDirections.actionSplashFragmentToFirstFragment())
        },3000)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}