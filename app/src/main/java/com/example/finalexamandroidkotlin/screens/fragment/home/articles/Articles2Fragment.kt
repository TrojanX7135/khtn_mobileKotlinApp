package com.example.finalexamandroidkotlin.screens.fragment.home.articles

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
import com.example.finalexamandroidkotlin.databinding.FragmentArticles2Binding
import com.example.finalexamandroidkotlin.model.Articles
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Articles2Fragment : Fragment() {

    private lateinit var binding: FragmentArticles2Binding
    private lateinit var mArticles: Articles
    private lateinit var homeActivity: HomeActivity
    private lateinit var layoutContainer: ViewPager2
    private lateinit var adapter: TagAdapter
    private lateinit var email: String
    private var isLike: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticles2Binding.inflate(layoutInflater)

        getObjectCurrent()
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutMain.visibility = View.GONE

        Handler().postDelayed(object: Runnable {
            override fun run() {
                init()
                setUpView()
                setOnClick()

                binding.progressBar.visibility = View.GONE
                binding.layoutMain.visibility = View.VISIBLE
            }

        }, 2000);

        return binding.root
    }

    private fun init() {
        homeActivity = HomeActivity()
        adapter = TagAdapter(requireContext(), mArticles.listTag!!.toList())
        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)
    }

    private fun getObjectCurrent() {
        email = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email

        val dbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
        dbRef.child("Articles_Current")
            .child(convertEmail(email))
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: MutableList<Articles> = mutableListOf()
                    list.clear()
                    for (data in snapshot.children) {
                        val articles: Articles? = data.getValue(Articles::class.java)
                        list.add(articles!!)
                    }
                    mArticles = list[0]
                    if (mArticles.listHeart !== null) {
                        for (i in 0 until mArticles.listHeart!!.size) {
                            if (mArticles.listHeart!![i].matches(email.toRegex())) {
                                isLike = true
                                break
                            }
                        }
                    } else {
                        isLike = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun convertEmail(email: String): String {
        val list: MutableList<String> = mutableListOf()
        val arr = email.split(".")
        list.add(arr[0])

        return list[0]
    }

    private fun setUpView() {
        Glide
            .with(requireContext())
            .load(mArticles.image)
            .into(binding.imageArticles)
        Glide
            .with(requireContext())
            .load(mArticles.avatarUser)
            .into(binding.avatarUser)
        if (isLike) {
            binding.imageStatusLike.setImageResource(R.drawable.image_like)
        } else {
            binding.imageStatusLike.setImageResource(R.drawable.image_unlike)
        }
        binding.textTitleArticles.text = mArticles.title
        binding.textUserName.text = mArticles.userName
        binding.textDate.text = mArticles.date
        binding.textDescriptionArticles.text = mArticles.description
        binding.rcvTag.adapter = adapter
    }

    private fun setOnClick() {
        binding.imageBack.setOnClickListener {
            layoutContainer.currentItem = homeActivity.flagArticles1Fragment
        }
    }

    override fun onResume() {
        super.onResume()

        getObjectCurrent()
        binding.layoutMain.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        Handler().postDelayed(object: Runnable{
            override fun run() {
                binding.layoutMain.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

                setUpView()
            }

        } , 2000)
    }
}