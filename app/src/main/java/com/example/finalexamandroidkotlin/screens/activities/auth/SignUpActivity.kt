package com.example.finalexamandroidkotlin.screens.activities.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.databinding.ActivitySignUpBinding
import com.example.finalexamandroidkotlin.func.ValidateString
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        setOnClick()
    }

    private fun init(): Unit {
        auth = FirebaseAuth.getInstance()
    }

    private fun setOnClick() {
        binding.textSignIn.setOnClickListener { finish() }
        binding.buttonSignUp.setOnClickListener{signUp()}
    }

    private fun signUp(): Unit {
        val email = binding.inputEmail.text.toString().trim()
        val pass = binding.inputPass.text.toString().trim()
        val userName = binding.inputUserName.text.toString().trim()

        val isNullInput: Boolean = ValidateString.isNull(email, pass, userName)
        val isEmail: Boolean = ValidateString.isEmail(email)
        val isPassword = ValidateString.isPassword(pass)

        if (isNullInput) {
            Toast.makeText(this@SignUpActivity, resources.getString(R.string.err_null), Toast.LENGTH_LONG).show()
            return
        }; if (!isEmail) {
            Toast.makeText(this@SignUpActivity, resources.getString(R.string.err_email_format), Toast.LENGTH_LONG).show()
            return;
        }; if (!isPassword)  {
            Toast.makeText(this@SignUpActivity, resources.getString(R.string.err_password_format), Toast.LENGTH_LONG).show()
            return
        }

        // show progress bar
        binding.progressBar.visibility = View.VISIBLE

        // handle sign up
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase_Message", "createUserWithEmail:success") // log result

                    // after create account -> update display name

                    // get user current
                    val user = FirebaseAuth.getInstance().currentUser

                    // push data update -> push display name
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(userName)
                        .build()
                    // handle update profile
                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            binding.progressBar.visibility = View.GONE
                            if (task.isSuccessful) {
                                Log.d("Firebase_Message", "Update profile success")
                                finish()
                            } else {
                                Log.d("Firebase_Message", "Update profile fail")
                                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    binding.progressBar.visibility = View.GONE
                    Log.w("Firebase_Message", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
    }
}