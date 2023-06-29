package com.example.finalexamandroidkotlin.screens.activities.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.databinding.ActivityForgetPassBinding
import com.example.finalexamandroidkotlin.func.ValidateString
import com.google.firebase.auth.FirebaseAuth

class ForgetPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClick()
    }

    private fun setOnClick(): Unit {
        binding.buttonSendEmail.setOnClickListener{forgetPassword()}
    }

    private fun forgetPassword() {
        val email: String = binding.inputEmail.text.toString().trim()

        // validate input
        if (email.isEmpty()) {
            Toast.makeText(this@ForgetPassActivity, resources.getString(R.string.err_null), Toast.LENGTH_LONG).show()
            return
        }; if (!ValidateString.isEmail(email)) {
            Toast.makeText(this@ForgetPassActivity, resources.getString(R.string.err_email_format), Toast.LENGTH_LONG).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        // handle send email reset password
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener{task -> kotlin.run{
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(this@ForgetPassActivity, resources.getString(R.string.email_sent), Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@ForgetPassActivity, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }}
    }
}