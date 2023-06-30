package com.example.finalexamandroidkotlin.adapter.vp2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalexamandroidkotlin.screens.fragment.home.profile.ArticlesFragment
import com.example.finalexamandroidkotlin.screens.fragment.home.profile.SpeciesFragment

class ProfileAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArticlesFragment()
            else -> SpeciesFragment()
        }
    }
}