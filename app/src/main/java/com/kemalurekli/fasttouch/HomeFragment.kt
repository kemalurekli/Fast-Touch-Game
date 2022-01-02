package com.kemalurekli.fasttouch

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.kemalurekli.fasttouch.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mAdView : AdView
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playagain.visibility = View.INVISIBLE
        binding.menubutton.visibility = View.INVISIBLE
        sharedPreferences = requireContext().getSharedPreferences("com.kemalurekli.fasttouch", Context.MODE_PRIVATE)
        val getFromSharePreferences = sharedPreferences.getInt("bestscore",0)
        val getFromSharePreferencesGameTime = sharedPreferences.getInt("gametimeinput",20)
        val userInputTime : Long = getFromSharePreferencesGameTime.toLong()*1000
        binding.tvBestScore.text = "Best Score \n $getFromSharePreferences"
        var gameMode : Long = 1000

        arguments?.let {
            val chosenMode = (HomeFragmentArgs.fromBundle(it).modeNumber).toInt()
            when(chosenMode){
                0-> gameMode = 1500
                1-> gameMode = 1000
                2-> gameMode = 500

            }
        }
        //ImageArray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        hideImages(gameMode)
        //if pressed any pictures
        binding.imageView.setOnClickListener {
            increaseScore()
        }
        binding.imageView2.setOnClickListener {
            increaseScore()
        }
        binding.imageView3.setOnClickListener {
            increaseScore()
        }
        binding.imageView4.setOnClickListener {
            increaseScore()
        }
        binding.imageView5.setOnClickListener {
            increaseScore()
        }
        binding.imageView6.setOnClickListener {
            increaseScore()
        }
        binding.imageView7.setOnClickListener {
            increaseScore()
        }
        binding.imageView8.setOnClickListener {
            increaseScore()
        }
        binding.imageView9.setOnClickListener {
            increaseScore()
        }
        binding.imageView10.setOnClickListener {
            increaseScore()
        }
        binding.imageView11.setOnClickListener {
            increaseScore()
        }
        binding.imageView12.setOnClickListener {
            increaseScore()
        }
        binding.imageView13.setOnClickListener {
            increaseScore()
        }
        binding.imageView14.setOnClickListener {
            increaseScore()
        }
        binding.imageView15.setOnClickListener {
            increaseScore()
        }
        binding.imageView16.setOnClickListener {
            increaseScore()
        }
        //CountDown Timer
        object : CountDownTimer(userInputTime, 1000) {
            override fun onFinish() {
                binding.timeText.text = "Time is up."
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                //After end of the game
                binding.menubutton.visibility = View.VISIBLE
                binding.playagain.visibility = View.VISIBLE
                binding.playagain.setOnClickListener {
                    score = 0
                    binding.scoreText.text = "Score : 0"
                    onViewCreated(view,savedInstanceState)
                }
                binding.menubutton.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToFirstFragment()
                    Navigation.findNavController(view).navigate(action)
                }
                //After end of the game

            }
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: " + millisUntilFinished / 1000

                binding.seekbarTime.isEnabled=false
                binding.seekbarTime.max=(userInputTime/1000).toInt()
                binding.seekbarTime.progress=(millisUntilFinished/1000).toInt()




                if(millisUntilFinished/1000 <10 ){
                    binding.timeText.setTextColor(resources.getColor(R.color.yellow))
                }
                if(millisUntilFinished/1000 < 5 ){
                    binding.timeText.setTextColor(resources.getColor(R.color.red))
                }
            }
        }.start()





        MobileAds.initialize(requireContext()) {}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }



    fun hideImages(gameMode : Long) {
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(16)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, gameMode)
            }
        }
        handler.post(runnable)
    }
    fun increaseScore() {
        score = score + 1
        binding.scoreText.text = "Score: $score"
        val sharedPreferences = requireContext().getSharedPreferences("com.kemalurekli.fasttouch", Context.MODE_PRIVATE)
        val getFromSharePreferences = sharedPreferences.getInt("bestscore",0)
        if (score>getFromSharePreferences){
            sharedPreferences.edit().putInt("bestscore",score).apply()
            binding.tvBestScore.text = "Best Score \n ${getFromSharePreferences+1}"
        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}