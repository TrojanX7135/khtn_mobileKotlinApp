package com.example.appplant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appplant.databinding.ActivityMainBinding
import com.example.appplant.databinding.ActivityMan2Binding

//import kotlinx.android.synthetic.main.activity_main.view.*


class man2 : AppCompatActivity() {
    private lateinit var buttons: Button
    private lateinit var binding: ActivityMan2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMan2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_man2)
        binding.buttons2.setOnClickListener {
            val i2  = Intent(this,man3::class.java)
            startActivity(i2)
        }

    }
}
