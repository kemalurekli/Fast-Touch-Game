package com.kemalurekli.fasttouch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kemalurekli.fasttouch.databinding.FragmentModeBinding

class ModeFragment : Fragment() {
    private var _binding: FragmentModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.easyButton.setOnClickListener {
            val action = ModeFragmentDirections.actionModeFragmentToHomeFragment("0")
            Navigation.findNavController(it).navigate(action)
        }
        binding.diffucultButton.setOnClickListener {
            val action = ModeFragmentDirections.actionModeFragmentToHomeFragment("1")
            Navigation.findNavController(it).navigate(action)
        }
        binding.hardButton.setOnClickListener {
            val action = ModeFragmentDirections.actionModeFragmentToHomeFragment("2")
            Navigation.findNavController(it).navigate(action)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}