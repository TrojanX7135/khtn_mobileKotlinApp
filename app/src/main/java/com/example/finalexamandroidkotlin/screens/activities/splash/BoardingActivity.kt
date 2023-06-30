package com.example.finalexamandroidkotlin.screens.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.vp2.BoardingAdapter
import com.example.finalexamandroidkotlin.database.app.AppDatabase
import com.example.finalexamandroidkotlin.databinding.ActivityBoardingBinding
import com.example.finalexamandroidkotlin.model.App
import com.example.finalexamandroidkotlin.screens.activities.auth.LoginActivity

class BoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardingBinding
    private lateinit var adapter: BoardingAdapter

    private var flagButton: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // init view
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkIsFirstInstallApp()
        setUpView()
        setOnClick();
    }

    private fun checkIsFirstInstallApp() {
        val app: App? = AppDatabase
            .getInstance(this)
            .appDAO()
            .getApp();

        if (app != null && app.isFirstInstallApp) {
            Log.d("Tag_First_Install_App", "False")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Log.d("Tag_First_Install_App", "True")
        }
    }

    private fun setUpView(): Unit {
        adapter = BoardingAdapter(this@BoardingActivity)
        binding.layoutContainerBoarding.adapter = adapter
        binding.progressBoarding.setViewPager(binding.layoutContainerBoarding)

        binding.layoutContainerBoarding.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    2 -> {
                        binding.buttonNextBoarding.text = resources.getString(R.string.sign_in)
                        flagButton = 1
                    }

                    else -> {
                        binding.buttonNextBoarding.text = resources.getString(R.string.next)
                        flagButton = 0
                    }
                }
            }
        })
    }

    private fun setOnClick(): Unit {
        binding.buttonNextBoarding.setOnClickListener {
            when (flagButton) {
                1 -> signIn();
                0 -> next();
            }
        }
    }

    private fun signIn(): Unit {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

        // luu ket qua da tai app
        val app: App = App(true)
        AppDatabase
            .getInstance(this)
            .appDAO()
            .insert(app)
    }

    private fun next(): Unit {
        binding.layoutContainerBoarding.currentItem += 1;
    }
}