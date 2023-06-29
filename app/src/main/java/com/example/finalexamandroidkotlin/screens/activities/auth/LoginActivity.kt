package com.example.finalexamandroidkotlin.screens.activities.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.ActivityLoginBinding
import com.example.finalexamandroidkotlin.func.ValidateString
import com.example.finalexamandroidkotlin.model.UserCurrent
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var share: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private val prefName: String = "prefs"
    private val remember: String = "remember"
    private val email: String = "email"
    private val password: String = "password"
    private val isChecked: String = "isChecked"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        checkUserRememberAccount();
        setOnClick()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        share = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        editor = share.edit()

        binding.chbRememberAccount.isChecked = share.getBoolean(remember, false)

        binding.inputEmail.setText(share.getString(email, ""))
        binding.inputPass.setText(share.getString(password, ""))
    }

    private fun checkUserRememberAccount(): Unit {
        val user = UserDatabase.getInstance(this@LoginActivity).userDAO().getUserCurrentAccount()

        if (user != null && user!!.isRemember) {
            binding.inputEmail.setText(user.email)
            binding.inputPass.setText(user.password)
            binding.chbRememberAccount.isChecked = user.isRemember
        } else {
            binding.inputEmail.setText("")
            binding.inputPass.setText("")
            binding.chbRememberAccount.isChecked = false
        }
    }

    private fun setOnClick(): Unit {
        binding.textSignUp.setOnClickListener { startActivity(Intent(this@LoginActivity, SignUpActivity::class.java)) }
        binding.buttonLogin.setOnClickListener{login()}
        binding.textForgotPassword.setOnClickListener{startActivity(Intent(this@LoginActivity, ForgetPassActivity::class.java))}
    }

    private fun login() {
        val email: String = binding.inputEmail.text.toString().trim()
        val pass: String = binding.inputPass.text.toString().trim()

        val isNullInput = ValidateString.isNull(email, pass)
        val isEmail = ValidateString.isEmail(email)

        // check validate input
        if (isNullInput) {
            Toast.makeText(this@LoginActivity, resources.getString(R.string.err_null), Toast.LENGTH_LONG).show()
            return
        }; if (!isEmail) {
            Toast.makeText(this@LoginActivity, resources.getString(R.string.err_email_format), Toast.LENGTH_LONG).show()
            return
        }

        // save to db
        handleSignIn(email, pass)
    }

    private fun handleSignIn(email: String, pass: String) {
        binding.progressBar.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase_Message", "signInWithEmail:success")
                    val user = auth.currentUser

                    /*
                    *   Khi đăng nhập sẽ có 2 trường hợp xảy ra
                    *   Trường hợp 1: Trong db userCurrent chưa có data => insert trực tiếp dữ liệu người dùng đã nhập
                    *   Trường hợp 2: Trong db đã có data => update data
                    */

                    var userCurrent = UserDatabase.getInstance(this@LoginActivity).userDAO().getUserCurrentAccount()
                    if (userCurrent == null) { // Trường hợp trong db chưa có dữ liệu
                        val isSelected = binding.chbRememberAccount.isChecked
                        userCurrent = UserCurrent(user!!.email.toString(), pass, user.displayName.toString(), isSelected)
                        // handle insert new user
                        UserDatabase.getInstance(this@LoginActivity).userDAO().rememberUserCurrent(userCurrent)
                    } else { // Trường hợp ngược lại
                        userCurrent.email = user!!.email.toString()
                        userCurrent.password = pass
                        userCurrent.userName = user.displayName.toString()
                        userCurrent.isRemember = binding.chbRememberAccount.isChecked
                        // update data in db
                        UserDatabase.getInstance(this@LoginActivity).userDAO().updateUserCurrentAccount(userCurrent)
                    }

                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    Log.w("Firebase_Message", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this@LoginActivity, task.exception.toString(), Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
    }
}