package com.example.appplant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appplant.databinding.ActivityMainBinding
import com.example.appplant.databinding.ActivityMan2Binding
import com.example.appplant.databinding.ActivityMan3Binding

//import kotlinx.android.synthetic.main.activity_main.view.*


class man3 : AppCompatActivity() {
    private lateinit var btn3: Button
    private lateinit var binding: ActivityMan3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMan3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_man3)
        //binding.textView.text = "Next"
        binding.buttons3.setOnClickListener {

            //val i3  = Intent(this,file activity của T.Anh::class.java)//chuyển đến màn hình lognin T.Anh đã làm
            //startActivity(i3)
        }

    }
}
