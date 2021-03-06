package com.kemalurekli.fasttouch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kemalurekli.fasttouch.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playButton.setOnClickListener {
            Navigation.findNavController(it).navigate(FirstFragmentDirections.actionFirstFragmentToModeFragment())
        }
        binding.settingsButton.setOnClickListener {
            Navigation.findNavController(it).navigate(FirstFragmentDirections.actionFirstFragmentToSettingsFragment())
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}