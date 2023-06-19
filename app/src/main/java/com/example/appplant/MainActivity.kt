package com.example.appplant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appplant.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //binding.textView.text = "Next"
        binding.buttonstart.setOnClickListener {
            val i  = Intent(this,man2::class.java)
            startActivity(i)
        }

    }
}
