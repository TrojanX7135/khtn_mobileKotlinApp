package com.example.finalexamandroidkotlin.screens.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnClick()
    }

    private fun setOnClick() {
        binding.imageBack.setOnClickListener { finish() }
    }
}