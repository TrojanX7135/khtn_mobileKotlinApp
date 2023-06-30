package com.example.finalexamandroidkotlin.screens.fragment.home.profile

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalexamandroidkotlin.adapter.rcv.PlantAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentSpeciesBinding
import com.example.finalexamandroidkotlin.model.Plant
import com.example.finalexamandroidkotlin.model.Species
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SpeciesFragment : Fragment() {

    private lateinit var binding: FragmentSpeciesBinding
    private lateinit var speciesTitle: MutableList<String>
    private lateinit var plants: MutableList<Plant>
    private lateinit var email: String
    private lateinit var plantAdapter: PlantAdapter
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpeciesBinding.inflate(layoutInflater)

        init()
        getTitles()

        return binding.root
    }

    private fun init() {
        speciesTitle = mutableListOf()
        plants = mutableListOf()
        email = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
        email = convertEmail(email)
        plantAdapter = PlantAdapter(requireContext(), plants, object : PlantAdapter.ClickItem {
            override fun chooseItem(plant: Plant) {}
        })
        dbRef = FirebaseDatabase.getInstance().reference
    }

    private fun getTitles() {
        binding.rcv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        // get list title species
        dbRef.child("Species").orderByChild("name")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    speciesTitle.clear()
                    for (data in snapshot.children) {
                        val title: String? = data!!.getValue(Species::class.java)!!.name
                        speciesTitle.add(title!!)
                    }

                    getData()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    private fun getData() {
        plants.clear()
        for (title in speciesTitle) {
            dbRef.child("Plants").child(title).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children) {
                        val plant: Plant? = data.getValue(Plant::class.java)
                        if (plant!!.listHeart != null) {
                            for (emailTemp in plant.listHeart!!) {
                                if (emailTemp.matches(email.toRegex())) {
                                    plants.add(plant)
                                    Log.d("CheckDieuKien", "True")
                                }
                            }
                        }
                    }

                    plantAdapter.setList(plants)
                    binding.rcv.adapter = plantAdapter

                    Log.d("CheckRun", "Running here")
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        }


        Handler().postDelayed(object : Runnable {
            override fun run() {
                binding.rcv.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }, 2000)
    }

    private fun convertEmail(email: String): String = email.split(".")[0]
}