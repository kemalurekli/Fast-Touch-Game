package com.kemalurekli.fasttouch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.Navigation
import com.kemalurekli.fasttouch.databinding.FragmentHomeBinding
import com.kemalurekli.fasttouch.databinding.FragmentSettingsBinding
import com.kemalurekli.fasttouch.databinding.FragmentSplashBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("com.kemalurekli.fasttouch", Context.MODE_PRIVATE)
        val getFromSharePreferencesGameTime = sharedPreferences.getInt("gametimeinput",20)
        binding.seekbarGameTime.progress=getFromSharePreferencesGameTime
        binding.tvGameTime.text = "Game Time : $getFromSharePreferencesGameTime s"


        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(SettingsFragmentDirections.actionSettingsFragmentToFirstFragment())
        }
        binding.seekbarGameTime.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                sharedPreferences.edit().putInt("gametimeinput",p1).apply()
                binding.tvGameTime.text = "Game Time : $p1 s"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}