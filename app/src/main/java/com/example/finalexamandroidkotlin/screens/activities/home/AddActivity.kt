package com.example.finalexamandroidkotlin.screens.activities.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.ActivityAddBinding
import com.example.finalexamandroidkotlin.model.Photo
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.UUID

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var dataEnd: Uri
    private lateinit var dbRef: DatabaseReference
    private lateinit var email: String
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get intent
        val intent = intent
        dataEnd = intent.getStringExtra("dataStart")!!.toUri()

        init()
        getEmailCurrent()

        Glide.with(this@AddActivity)
            .load(dataEnd)
            .into(binding.image)

        setOnClick()
    }

    private fun init() {
        dbRef = FirebaseDatabase.getInstance().reference
        storageRef = FirebaseStorage.getInstance().reference
    }

    private fun setOnClick() {
        binding.buttonAdd.setOnClickListener { add() }
        binding.imageBack.setOnClickListener { finish() }
    }

    private fun add() {
        val tag: String = binding.inputTagImage.text.toString().trim()
        val id: String = genIdAuto()
        val image: String = convertImageToBase64(dataEnd)

        if (tag.isEmpty()) {
            Toast.makeText(this@AddActivity, resources.getString(R.string.err_null), Toast.LENGTH_LONG).show()
            return
        }


        val photo: Photo = Photo(id, image, tag)

        dbRef.child("Photo_User").child(email).child(id).setValue(photo)
        Toast.makeText(this@AddActivity, resources.getString(R.string.message_insert_success), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun genIdAuto(): String = UUID.randomUUID().toString()

    private fun getEmailCurrent() {
        email = UserDatabase.getInstance(this@AddActivity).userDAO().getUserCurrentAccount()!!.email
        email = convertEmail(email)
    }

    private fun convertEmail(email: String): String = email.split(".")[0]

    @SuppressLint("Recycle")
    private fun convertImageToBase64(input: Uri):String {
        return try {
            var image: String = ""

            val inputStream = contentResolver.openInputStream(input)
            val mBitmap = BitmapFactory.decodeStream(inputStream)
            val stream = ByteArrayOutputStream()
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val bytes = stream.toByteArray()
            image = Base64.encodeToString(bytes, Base64.DEFAULT)
            inputStream!!.close()

            image
        } catch (ex: Exception) {
            ""
        }
    }
}