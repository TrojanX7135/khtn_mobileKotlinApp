package com.example.finalexamandroidkotlin.screens.activities.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.vp2.HomeAdapter
import com.example.finalexamandroidkotlin.databinding.ActivityHomeBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class HomeActivity : AppCompatActivity() {

    val flagHomeFragment = 0
    val flagProfileFragment = 1
    val flagSpecies1Fragment = 2
    val flagSpecies2Fragment = 3
    val flagSpecies3Fragment = 4
    val flagArticles1Fragment = 5
    val flagArticles2Fragment = 6

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    // lưu giữ trạng thái giữa các tab
    // flag = 1 => home fragment / else => fragment profile
    private var flagBottom = flagHomeFragment

    // check permission camera
    private val CAMERA_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        setOnClick()
    }

    @SuppressLint("ResourceType")
    private fun init() {
        // init bottom view
        if (flagBottom == flagHomeFragment || flagBottom == flagSpecies1Fragment || flagBottom == flagSpecies2Fragment || flagBottom == flagSpecies3Fragment || flagBottom == flagArticles2Fragment || flagBottom == flagArticles1Fragment) {
            initHomeBottom()
        } else {
            initProfileBottom()
        }

        // enable view pager swipe
        binding.layoutContainerMain.isUserInputEnabled = false

        // set up vpg
        homeAdapter = HomeAdapter(this@HomeActivity)
        binding.layoutContainerMain.adapter = homeAdapter
    }

    private fun setOnClick() {
        binding.itemHome.setOnClickListener { home() }
        binding.itemProfile.setOnClickListener { profile() }
        binding.imageAdd.setOnClickListener { add() }
    }

    private fun home() {
        flagBottom = flagHomeFragment
        initHomeBottom()
        binding.layoutContainerMain.currentItem = flagBottom
    }

    private fun profile() {
        flagBottom = flagProfileFragment
        initProfileBottom()
        binding.layoutContainerMain.currentItem = flagBottom
    }

    private fun initHomeBottom() {
        binding.iconHome.setColorFilter(
            ContextCompat.getColor(
                this, R.color.color_icon_bottom_selected
            ), PorterDuff.Mode.SRC_IN
        )
        binding.textHome.setTextColor(resources.getColor(R.color.color_icon_bottom_selected))
        binding.iconProfile.setColorFilter(
            ContextCompat.getColor(
                this, R.color.color_icon_bottom_unselected
            ), PorterDuff.Mode.SRC_IN
        )
        binding.textProfile.setTextColor(resources.getColor(R.color.color_icon_bottom_unselected))
    }

    private fun initProfileBottom() {
        binding.iconHome.setColorFilter(
            ContextCompat.getColor(
                this, R.color.color_icon_bottom_unselected
            ), PorterDuff.Mode.SRC_IN
        )
        binding.textHome.setTextColor(resources.getColor(R.color.color_icon_bottom_unselected))
        binding.iconProfile.setColorFilter(
            ContextCompat.getColor(
                this, R.color.color_icon_bottom_selected
            ), PorterDuff.Mode.SRC_IN
        )
        binding.textProfile.setTextColor(resources.getColor(R.color.color_icon_bottom_selected))
    }

    private fun add() {
        // check permission camera
//        checkPermissionCamera()
        checkPermission()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this@HomeActivity, "Permission denied", Toast.LENGTH_LONG).show()
        } else {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"

            resultLauncher.launch(intent)
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            Log.d("CheckData", data!!.data.toString())

            if (data.data != null) {
                val intent: Intent = Intent(this@HomeActivity, AddActivity::class.java)
                val dataStart: String = data.data.toString()
                intent.putExtra("dataStart", dataStart)
                startActivity(intent)
            }
        }
    }

    private fun checkPermission() {
        val permission = (object: PermissionListener {
            override fun onPermissionGranted() {
//                Toast.makeText(this@HomeActivity, "Permission granted", Toast.LENGTH_LONG).show()
                Log.d("Check_Permission", "Permission granted")
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                Toast.makeText(this@HomeActivity, "Permission denied", Toast.LENGTH_LONG).show()
                Log.d("Check_Permission", "Permission denied")
            }

        })

        TedPermission.create()
            .setPermissionListener(permission)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
            .check();

    }
}