package com.example.finalexamandroidkotlin.adapter.vp2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalexamandroidkotlin.screens.fragment.boarding.OneFragment
import com.example.finalexamandroidkotlin.screens.fragment.boarding.ThreeFragment
import com.example.finalexamandroidkotlin.screens.fragment.boarding.TwoFragment

class BoardingAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment()
            1 -> TwoFragment();
            2 -> ThreeFragment();
            else -> OneFragment();
        }
    }
}