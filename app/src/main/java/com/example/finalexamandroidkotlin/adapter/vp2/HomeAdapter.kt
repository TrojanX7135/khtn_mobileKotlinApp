package com.example.finalexamandroidkotlin.adapter.vp2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalexamandroidkotlin.screens.fragment.home.HomeFragment
import com.example.finalexamandroidkotlin.screens.fragment.home.ProfileFragment
import com.example.finalexamandroidkotlin.screens.fragment.home.articles.Articles1Fragment
import com.example.finalexamandroidkotlin.screens.fragment.home.articles.Articles2Fragment
import com.example.finalexamandroidkotlin.screens.fragment.home.species.Species1Fragment
import com.example.finalexamandroidkotlin.screens.fragment.home.species.Species2Fragment
import com.example.finalexamandroidkotlin.screens.fragment.home.species.Species3Fragment

class HomeAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            2 -> Species1Fragment()
            3 -> Species2Fragment()
            4 -> Species3Fragment()
            5 -> Articles1Fragment()
            6 -> Articles2Fragment()
            else -> HomeFragment()
        }
    }
}