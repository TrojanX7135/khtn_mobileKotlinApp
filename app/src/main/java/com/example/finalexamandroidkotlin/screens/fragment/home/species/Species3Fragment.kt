package com.example.finalexamandroidkotlin.screens.fragment.home.species

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.TagAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentSpecies3Binding
import com.example.finalexamandroidkotlin.model.Plant
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Species3Fragment : Fragment() {

    private lateinit var binding: FragmentSpecies3Binding
    private lateinit var listTag: MutableList<String>
    private lateinit var dbRef: DatabaseReference
    private lateinit var tagAdapter: TagAdapter
    private var mPlant: Plant? = null
    private lateinit var email: String
    private lateinit var homeActivity: HomeActivity
    private lateinit var layoutContainer: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecies3Binding.inflate(layoutInflater)

        init()
        getObjectCurrent()
        binding.layoutMain.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        Handler().postDelayed(object: Runnable{
            override fun run() {
                setData()
                setOnClick()

                binding.layoutMain.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }

        }, 2000)

        return binding.root
    }

    private fun setOnClick() {
        binding.imageBack.setOnClickListener { layoutContainer.currentItem = homeActivity.flagSpecies2Fragment }
        binding.imageLike.setOnClickListener {
            if (mPlant!!.listHeart == null) {
                val list: MutableList<String> = mutableListOf()
                list.add(email)

                // update
                mPlant!!.listHeart = list
                dbRef.child("Plants").child(mPlant!!.categories!!.name.toString()).child(mPlant!!.id.toString()).setValue(mPlant)
                dbRef.child("Plant_Current").child(convertEmail(email)).child("1").setValue(mPlant)
            } else {
                var flag: Int = 0
                for (emailTemp in mPlant!!.listHeart!!) {
                    if (emailTemp.matches(email.toRegex())) {
                        ++flag
                        mPlant!!.listHeart!!.remove(emailTemp)
                        break
                    }
                }

                if (flag == 0) {
                    mPlant!!.listHeart!!.add(email)
                }

                dbRef.child("Plants").child(mPlant!!.categories!!.name.toString()).child(mPlant!!.id.toString()).setValue(mPlant)
                dbRef.child("Plant_Current").child(convertEmail(email)).child("1").setValue(mPlant)
            }

            binding.layoutMain.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            getObjectCurrent()

            Handler().postDelayed(object: Runnable{
                override fun run() {
                    setData()
                    binding.layoutMain.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }, 2000)
        }
    }

    private fun init() {
        listTag = mutableListOf()
        dbRef = FirebaseDatabase.getInstance().reference
        tagAdapter = TagAdapter(requireContext(), listTag)
        homeActivity = HomeActivity()
        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)
    }

    private fun getObjectCurrent() {
        email = convertEmail(
            UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
        )
        dbRef.child("Plant_Current").child(email)
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: MutableList<Plant> = mutableListOf()
                    for (data in snapshot.children) {
                        val plant: Plant? = data.getValue(Plant::class.java)
                        list.add(plant!!)
                    }

                   if (list.size > 0) {
                       mPlant = list[0]
                   }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun convertEmail(email: String): String = email.split(".")[0]

    private fun setData() {
        if (mPlant != null) {
            Glide.with(requireContext())
                .load(mPlant!!.image)
                .into(binding.imagePlant)
            listTag = mPlant!!.listTag!!
            val point = mPlant!!.point
            binding.point.rating = point!!.toFloat()
            binding.textPoint.text = point.toString().subSequence(0, 3)
            binding.textKingdom.text = mPlant!!.kingdom
            binding.textCategory.text = mPlant!!.categories!!.name
            binding.textDescriptionPlant.text = mPlant!!.description
            binding.textPlantName.text = mPlant!!.name

            if (mPlant!!.listHeart != null) {
                var flag: Int = 0
                for (emailTemp in mPlant!!.listHeart!!) {
                    if (emailTemp.matches(email.toRegex())) {
                        binding.imageLike.setImageResource(R.drawable.image_like)
                        ++flag
                        break
                    }
                }

                if (flag == 0) {
                    binding.imageLike.setImageResource(R.drawable.image_unlike)
                }
            } else {
                binding.imageLike.setImageResource(R.drawable.image_unlike)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        getObjectCurrent()
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutMain.visibility = View.GONE

        Handler().postDelayed(object: Runnable{
            override fun run() {
                binding.progressBar.visibility = View.GONE
                binding.layoutMain.visibility = View.VISIBLE
                setData()
            }

        }, 2000);
    }
}