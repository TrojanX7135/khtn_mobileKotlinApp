package com.example.finalexamandroidkotlin.screens.fragment.home.species

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.PlantAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentSpecies2Binding
import com.example.finalexamandroidkotlin.model.Plant
import com.example.finalexamandroidkotlin.model.Species
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID
import kotlin.random.Random

class Species2Fragment : Fragment() {

    private lateinit var binding: FragmentSpecies2Binding
    private lateinit var homeActivity: HomeActivity
    private lateinit var list: MutableList<Plant>
    private lateinit var adapter: PlantAdapter
    private var mSpecies: Species? = null
    private lateinit var email: String
    private lateinit var dbRef: DatabaseReference
    private lateinit var layoutContainer: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecies2Binding.inflate(layoutInflater)

        init()
        getObjectCurrent()

        Handler().postDelayed(object: Runnable {
            override fun run() {
                setOnClick()
            }
        }, 2000)

        return binding.root
    }

    private fun init() {
        binding.rcv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().reference

        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)

        dbRef = FirebaseDatabase.getInstance().reference
        homeActivity = HomeActivity()
        list = mutableListOf()
        adapter = PlantAdapter(requireContext(), list, object: PlantAdapter.ClickItem {
            override fun chooseItem(plant: Plant) {
                // handle save plant current
                dbRef.child("Plant_Current").child(convertEmail(email)).child("1")
                    .setValue(plant)
                layoutContainer.currentItem = homeActivity.flagSpecies3Fragment
            }
        })
    }

    private fun getObjectCurrent() {
        email = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
        dbRef.child("Species_Current").child(convertEmail(email))
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: MutableList<Species> = mutableListOf()
                    list.clear()
                    for (data in snapshot.children) {
                        val species: Species? = data.getValue(Species::class.java)
                        list.add(species!!)
                        Log.d("CheckSpeiecs", "Name: ${species.name}")
                    }

                    mSpecies = list[0]
                    if (mSpecies!!.name != null) binding.textCategory.text = mSpecies!!.name

                    getData()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    private fun convertEmail(email: String) = email.split(".")[0]

    private fun setOnClick() {
        binding.imageBack.setOnClickListener { layoutContainer.currentItem = homeActivity.flagSpecies1Fragment }
        binding.imageSearch.setOnClickListener {
            val key: String = binding.inputSearch.text.toString().trim()
            if (key.isEmpty()) {
                getObjectCurrent()
                getData()
                return@setOnClickListener
            }

            val ls: MutableList<Plant> = mutableListOf()
            for (data in list) {
                if (data.name!!.contains(key.toRegex())) {
                    ls.add(data)
                }
            }

            adapter.setList(ls)
            binding.rcv.adapter = adapter
        }
    }

    private fun addData() {
        list.clear()
        list.add(Plant(genIdAuto(), resources.getString(R.string.plant_name_1), resources.getString(R.string.plant_image_1), getListTag(), null, randomDouble(), randomKingdom(), mSpecies, resources.getString(R.string.plant_description_1)))
        list.add(Plant(genIdAuto(), resources.getString(R.string.plant_name_1), resources.getString(R.string.plant_image_1), getListTag(), null, randomDouble(), randomKingdom(), mSpecies, resources.getString(R.string.plant_description_1)))
        list.add(Plant(genIdAuto(), resources.getString(R.string.plant_name_1), resources.getString(R.string.plant_image_1), getListTag(), null, randomDouble(), randomKingdom(), mSpecies, resources.getString(R.string.plant_description_1)))
        list.add(Plant(genIdAuto(), resources.getString(R.string.plant_name_1), resources.getString(R.string.plant_image_1), getListTag(), null, randomDouble(), randomKingdom(), mSpecies, resources.getString(R.string.plant_description_1)))

        for (plant in list) {
            dbRef.child("Plants").child(mSpecies!!.name.toString()).child(plant.id.toString()).setValue(plant)
        }
    }

    private fun genIdAuto(): String = UUID.randomUUID().toString()

    private fun getListTag(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        for (i in 0 until randomInt()) {
            list.add("Tag $i")
        }

        return list
    }

    private fun randomInt(): Int {
        return Random.nextInt(1, 4)
    }

    private fun randomDouble(): Double {
        return Random.nextDouble(1.0, 5.0) // trả về một số ngẫu nhiên từ 1 đến 5 với step là một số thực
    }

    private fun randomKingdom(): String {
        return if (randomInt() == 1) {
            "FARM"
        } else {
            "HOME"
        }
    }

    private fun getData() {
        dbRef.child("Plants").child(mSpecies!!.name.toString()).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children) {
                    val plant: Plant? = data.getValue(Plant::class.java)
                    list.add(plant!!)
                }

                binding.progressBar.visibility = View.GONE
                binding.rcv.visibility = View.VISIBLE

                loadData()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), resources.getString(R.string.err_get_data), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadData() {
        adapter.setList(list)
        binding.rcv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        binding.rcv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        getObjectCurrent()
    }
}