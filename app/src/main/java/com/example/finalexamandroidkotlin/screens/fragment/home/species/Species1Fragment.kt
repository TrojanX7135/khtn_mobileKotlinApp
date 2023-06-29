package com.example.finalexamandroidkotlin.screens.fragment.home.species

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.SpeciesAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentSpecies1Binding
import com.example.finalexamandroidkotlin.model.Species
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

class Species1Fragment : Fragment() {

    private lateinit var binding: FragmentSpecies1Binding
    private lateinit var layoutContainer: ViewPager2
    private lateinit var homeActivity: HomeActivity
    private lateinit var list: MutableList<Species>
    private lateinit var dbRef: DatabaseReference
    private lateinit var adapter: SpeciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecies1Binding.inflate(layoutInflater)

        init()
//        addData()
//        addDataToFirebase()
        getData()
        setOnClick()

        return binding.root
    }

    private fun init() {
        list = mutableListOf()
        homeActivity = HomeActivity()
        dbRef = FirebaseDatabase.getInstance().reference
        adapter = SpeciesAdapter(requireContext(), list, object : SpeciesAdapter.ClickItem {
            override fun chooseItem(species: Species) {
                handleChooseItem(species)
            }
        })
        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)

        binding.progressBar.visibility = View.VISIBLE
        binding.rcvSpecies.visibility = View.GONE
    }

    private fun setOnClick() {
        binding.imageBack.setOnClickListener {
            layoutContainer.currentItem = homeActivity.flagHomeFragment
        }

        binding.imageSearch.setOnClickListener {
            val key = binding.inputSearch.text.toString().trim()

            if (key.isEmpty()) {
                getData()
                return@setOnClickListener
            }

            for (data in list) {
                val ls: MutableList<Species> = mutableListOf()
                if (data.name!!.contains(key.toRegex())) {
                    ls.add(data)

                    adapter.setList(ls)
                    binding.rcvSpecies.adapter = adapter
                }
            }
        }
    }

//    private fun addData() {
//        list.add(Species(genIdAuto(), "Cactus"))
//        list.add(Species(genIdAuto(), "Dracaena"))
//        list.add(Species(genIdAuto(), "Palm"))
//        list.add(Species(genIdAuto(), "Anthurium"))
//    }

    private fun addDataToFirebase() {
        for (i in 0 until list.size) {
            list[i].id?.let {
                dbRef
                    .child(resources.getString(R.string.species_table))
                    .child(it)
                    .setValue(list[i])
            }
        }
    }

    private fun genIdAuto(): String = UUID.randomUUID().toString()

    private fun getData() {
        dbRef.child(resources.getString(R.string.species_table))
            .orderByChild("name")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (data in snapshot.children) {
                        val species: Species? = data.getValue(Species::class.java)
                        species?.let { list.add(it) }
                    }

                    binding.rcvSpecies.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE

                    loadData()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun loadData() {
        adapter.setList(list)
        binding.rcvSpecies.adapter = adapter
    }

    private fun handleChooseItem(species: Species) {
        val email = convertEmail(
            UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
        )

        dbRef.child("Species_Current")
            .child(email)
            .child("1")
            .setValue(species)

        layoutContainer.currentItem = homeActivity.flagSpecies2Fragment
    }

    private fun convertEmail(email: String): String = email.split(".")[0]
}