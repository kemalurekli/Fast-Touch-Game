package com.kemalurekli.fasttouch

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import com.kemalurekli.fasttouch.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences
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
        sharedPreferences = requireContext().getSharedPreferences("com.kemalurekli.fasttouch", Context.MODE_PRIVATE)
        val getFromSharePreferences = sharedPreferences.getInt("bestscore",0)
        binding.tvBestScore.text = "Best Score \n $getFromSharePreferences"
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        object : CountDownTimer(15500, 1000) {
            override fun onFinish() {
                binding.timeText.text = "Time is up."
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                //Alert
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    //Restart
                    score = 0
                    binding.scoreText.text = "Score : 0"
                    onViewCreated(view,savedInstanceState)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    val action = HomeFragmentDirections.actionHomeFragmentToFirstFragment()
                    Navigation.findNavController(view).navigate(action)
                    Toast.makeText(requireContext(), "Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: " + millisUntilFinished / 1000
                if(millisUntilFinished/1000 <10 ){
                    binding.timeText.setTextColor(resources.getColor(R.color.yellow))
                }
                if(millisUntilFinished/1000 < 5 ){
                    binding.timeText.setTextColor(resources.getColor(R.color.red))
                }
            }
        }.start()
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