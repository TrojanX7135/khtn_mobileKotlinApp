package com.example.finalexamandroidkotlin.screens.fragment.home.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.ArticlesAdapter
import com.example.finalexamandroidkotlin.adapter.rcv.PlantAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentArticlesBinding
import com.example.finalexamandroidkotlin.model.Articles
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var adapter: ArticlesAdapter
    private lateinit var list: MutableList<Articles>
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(layoutInflater)

        init()
        getData()

        return binding.root
    }

    private fun init() {
        binding.rcv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().reference
        list = mutableListOf()
        adapter = ArticlesAdapter(requireContext(), list, object: ArticlesAdapter.ClickItem {
            override fun clickItem(articles: Articles) {

            }

            override fun handleSave(articles: Articles) {

            }

            override fun handleLike(articles: Articles) {

            }
        })

        email = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email
    }

    private fun getData() {
        dbRef.child(resources.getString(R.string.articles_table))
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (data in snapshot.children) {
                        val articles: Articles? = data.getValue(Articles::class.java)
                        if (articles!!.listSave != null) {
                            for (emailTemp in articles.listSave!!) {
                                if (emailTemp.matches(email.toRegex())) {
                                        list.add(articles)
                                }
                            }
                        }
                    }

                    loadData()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    private fun loadData() {
        adapter.setList(list)
        binding.rcv.adapter = adapter

        Log.d("CheckList", "${list.size}")

        binding.rcv.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }
}