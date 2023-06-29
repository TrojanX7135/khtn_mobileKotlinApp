package com.example.finalexamandroidkotlin.screens.fragment.home

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.PhotoAdapter
import com.example.finalexamandroidkotlin.adapter.rcv.PlantTypeAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentHomeBinding
import com.example.finalexamandroidkotlin.model.Photo
import com.example.finalexamandroidkotlin.model.PlantType
import com.example.finalexamandroidkotlin.screens.activities.home.CameraActivity
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listPlant: MutableList<PlantType>
    private lateinit var listPhoto: MutableList<Photo>
    private lateinit var plantTypeAdapter: PlantTypeAdapter
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var layoutContainer: ViewPager2
    private lateinit var dbRef: DatabaseReference
    private lateinit var email: String
    private lateinit var homeActivity: HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        init()
        getData()
        loadData()
        setOnClick()

        return binding.root
    }

    private fun init() {
        homeActivity = HomeActivity()
        dbRef = FirebaseDatabase.getInstance().reference

        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)

        listPlant = mutableListOf()
        listPhoto = mutableListOf()

        plantTypeAdapter = PlantTypeAdapter(requireContext().applicationContext, listPlant)
        photoAdapter = PhotoAdapter(requireContext().applicationContext, listPhoto, object: PhotoAdapter.ClickItem{
            override fun chooseItem(photo: Photo) {
                val dialog = Dialog(activity!!)
                //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.dialog_item_photo)
                val window = dialog.window
                window!!.setLayout(
                    AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT
                )
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val imageClose: ImageView = dialog.findViewById(R.id.imageCloseDialog)
                val imageView: ImageView = dialog.findViewById(R.id.imagePhoto)

                val base64String = "${photo.uri}"
                val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

                imageView.setImageBitmap(bitmap)

                imageClose.setOnClickListener { dialog.cancel() }
            }

            override fun clearItem(photo: Photo) {
                AlertDialog.Builder(requireContext())
                    .setTitle(resources.getString(R.string.confirm_delete_item))
                    .setMessage("${resources.getString(R.string.do_you_want_to_delete)}${photo.tag}")
                    .setPositiveButton("No", null)
                    .setNegativeButton("Yes", object: DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dbRef.child("Photo_User").child(email).child(photo.id!!).removeValue()
                            Toast.makeText(requireContext(), resources.getString(R.string.message_delete_successful), Toast.LENGTH_LONG).show()
                        }
                    })
                    .show()
            }
        })

        val userName: String = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.userName
        email = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
        email = convertEmail(email)
        if (userName != null) binding.textHelloUserName.text = "Hello $userName"
    }

    private fun convertEmail(email: String): String = email.split(".")[0]

    private fun getData() {
        // get list plant
        listPlant.add(PlantType(1, "Home Plants", 68))
        listPlant.add(PlantType(2, "Huge Plants", 102))
        plantTypeAdapter.setList(listPlant)

        // get list photography
        getListPhoto()
    }

    private fun getListPhoto() {
        dbRef.child("Photo_User").child(email).orderByChild("id")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    listPhoto.clear()
                    for (data in snapshot.children) {
                        val photo: Photo? = data.getValue(Photo::class.java)
                        listPhoto.add(photo!!)
                    }

                    loadDataPhoto()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    private fun loadDataPhoto() {
        photoAdapter.setList(listPhoto)
        binding.rcvPhoto.adapter = photoAdapter
    }

    private fun loadData() {
        binding.rcvPlants.adapter = plantTypeAdapter
    }

    private fun setOnClick() {
        binding.buttonAddingNew.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(requireContext(), CameraActivity::class.java))
            } else {
                // Yêu cầu cấp quyền truy cập camera
                checkPermission()
            }
        }

        binding.buttonSpecies.setOnClickListener { layoutContainer.currentItem = homeActivity.flagSpecies1Fragment }
        binding.buttonArticles.setOnClickListener { layoutContainer.currentItem = homeActivity.flagArticles1Fragment }
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
            .setPermissions(Manifest.permission.CAMERA)
            .check();
    }
}