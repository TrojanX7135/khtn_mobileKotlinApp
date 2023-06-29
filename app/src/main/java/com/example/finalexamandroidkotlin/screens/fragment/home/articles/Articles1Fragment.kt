package com.example.finalexamandroidkotlin.screens.fragment.home.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.rcv.ArticlesAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentArticles1Binding
import com.example.finalexamandroidkotlin.model.Articles
import com.example.finalexamandroidkotlin.screens.activities.home.HomeActivity
import com.google.android.play.core.integrity.i
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

class Articles1Fragment : Fragment() {

    private lateinit var binding: FragmentArticles1Binding
    private lateinit var list: MutableList<Articles>
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var database: DatabaseReference
    private lateinit var homeActivity: HomeActivity
    private lateinit var layoutContainer: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticles1Binding.inflate(layoutInflater)

        init()
        getDatabase()
        setOnClick()

        return binding.root
    }

    private fun setOnClick(): Unit {
        binding.imageBack.setOnClickListener {
            layoutContainer.currentItem = homeActivity.flagHomeFragment
        }
    }

    private fun init() {
        list = mutableListOf()
        articlesAdapter =
            ArticlesAdapter(requireContext(), list, object : ArticlesAdapter.ClickItem {
                override fun clickItem(articles: Articles) {
                    handleChooseArticlesItem(articles)
                }

                override fun handleSave(articles: Articles) {
                    handleSaveArticles(articles)
                }

                override fun handleLike(articles: Articles) {
                    handleLikeArticles(articles)
                }
            })
        database = FirebaseDatabase.getInstance().reference
        homeActivity = HomeActivity()
        layoutContainer = requireActivity().findViewById(R.id.layoutContainerMain)
    }

    private fun handleSaveArticles(articles: Articles) {
        var flag: Int = 0
        val email: String =
            UserDatabase
                .getInstance(requireContext())
                .userDAO()
                .getUserCurrentAccount()!!
                .email
        if (articles.listSave != null) {
            for (s in articles.listSave!!) {
                if (s.matches(email.toRegex())) {
                    // update save to unsave
                    articles.listSave!!.remove(email)
                    flag++
                    break;
                }
            }

            if (flag == 0) {
                // update un save to save
                articles.listSave!!.add(email)
            }
        } else {
            val listSave: MutableList<String> = mutableListOf()
            listSave.add(email)
            articles.listSave = listSave
        }

        // handle update
        val databaseRef = FirebaseDatabase.getInstance().reference
        databaseRef
            .child(resources.getString(R.string.articles_table))
            .child(articles.id.toString())
            .setValue(articles)

//        Toast.makeText(requireContext(), "${articles.listSave!![1]}", Toast.LENGTH_LONG).show()
    }

    private fun handleLikeArticles(articles: Articles) {
        var flag: Int = 0
        val email: String =
            UserDatabase
                .getInstance(requireContext())
                .userDAO()
                .getUserCurrentAccount()!!
                .email
        if (articles.listHeart == null) {
            val listHeart: MutableList<String> = mutableListOf()
            listHeart.add(email)
            articles.listHeart = listHeart
        } else {
            for (i in 0 until articles.listHeart!!.size) {
                if (articles.listHeart!![i].matches(email.toRegex())) {
                    ++flag
                    articles.listHeart!!.remove(email)
                    break
                }
            }

            if (flag == 0) {
                articles.listHeart!!.add(email)
            }
        }

        // handle update
        val databaseRef = FirebaseDatabase.getInstance().reference
        databaseRef
            .child(resources.getString(R.string.articles_table))
            .child(articles.id.toString())
            .setValue(articles)
    }

    private fun handleChooseArticlesItem(articles: Articles) {
        val email: String = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!.email

        val databaseRef = FirebaseDatabase.getInstance().reference
        databaseRef.child("Articles_Current")
            .child(convertEmail(email))
            .child("1")
            .setValue(articles)

        // replace fragment
        layoutContainer.currentItem = homeActivity.flagArticles2Fragment
    }

    private fun addDataIntoList() {
        list.add(
            Articles(
                genIdAuto(),
                resources.getString(R.string.title_articles_1),
                resources.getString(R.string.content_articles_1),
                resources.getString(R.string.link_image_articles_1),
                resources.getString(R.string.avatar_user_articles_1),
                "Shivani Vora",
                "2019. 01. 01",
                mutableListOf(),
                mutableListOf(),
                addTag(1)
            )
        )
        list.add(
            Articles(
                genIdAuto(),
                resources.getString(R.string.title_articles_2),
                resources.getString(R.string.content_articles_2),
                resources.getString(R.string.link_image_articles_2),
                resources.getString(R.string.avatar_user_articles_2),
                "Elizabeth",
                "2019. 01. 01",
                mutableListOf(),
                mutableListOf(),
                addTag(2)
            )
        )
    }

    private fun addDataIntoFirebase() {
        for (i in 0 until list.size) {
            list[i].id?.let {
                database.child(resources.getString(R.string.articles_table)).child(it)
                    .setValue(list[i])
            }
        }
    }

    private fun genIdAuto(): String = UUID.randomUUID().toString()

    private fun addTag(flag: Int): MutableList<String> {
        var list: MutableList<String> = mutableListOf()

        when (flag) {
            1 -> {
                list.clear()
                list.add("TagDemo 1")
                list.add("TagDemo 2")
            }

            2 -> {
                list.clear()
                list.add("TagDemo 1")
            }

            3 -> {
                list.clear()
                list.add("TagDemo 1")
                list.add("TagDemo 2")
                list.add("TagDemo 3")
            }

            else -> {
                list.clear()
                list.add("TagDemo 1")
                list.add("TagDemo 2")
            }
        }

        return list
    }

    private fun getDatabase() {
        database.child(resources.getString(R.string.articles_table)).orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (data in snapshot.children) {
                        val articles: Articles? = data.getValue(Articles::class.java)
                        list.add(articles!!)
                    }

                    setList()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.err_get_data),
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }

    private fun setList() {
        articlesAdapter.setList(list)
        binding.rcvArticles.adapter = articlesAdapter
    }

    private fun convertEmail(email: String): String {
        val arr = email.split(".");

        return arr[0]
    }
}