package com.example.finalexamandroidkotlin.screens.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.adapter.vp2.ProfileAdapter
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.databinding.FragmentProfileBinding
import com.example.finalexamandroidkotlin.model.UserCurrent

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mUser: UserCurrent
    private lateinit var adapter: ProfileAdapter

    val flagTab1: Int = 0
    val flagTab2:Int = 1
    var flagTab = flagTab1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        init()
        getUserCurrent()
        setUpView()
        setOnclick()

        return binding.root
    }

    private fun init() {
        adapter = ProfileAdapter(requireActivity())
        binding.layoutContainerProfile.adapter = adapter
    }

    private fun getUserCurrent() {
        mUser = UserDatabase.getInstance(requireContext()).userDAO().getUserCurrentAccount()!!
    }

    private fun setUpView() {
        binding.textUsrName.text = mUser.userName
        binding.layoutContainerProfile.isUserInputEnabled = false
        when (flagTab) {
            0 -> {
                chooseTab1()
            }
            else -> {
                chooseTab2()
            }
        }
    }

    private fun chooseTab1() {
        binding.textTab1.setBackgroundResource(R.drawable.bg_tab_selected)
        binding.textTab1.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_light))

        binding.textTab2.setBackgroundResource(R.drawable.bg_tab_un_select)
        binding.textTab2.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_dark))

        flagTab = flagTab1
        binding.layoutContainerProfile.currentItem = flagTab
    }

    private fun chooseTab2() {
        binding.textTab1.setBackgroundResource(R.drawable.bg_tab_un_select)
        binding.textTab1.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_dark))

        binding.textTab2.setBackgroundResource(R.drawable.bg_tab_selected)
        binding.textTab2.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_light))

        flagTab = flagTab2
        binding.layoutContainerProfile.currentItem = flagTab
    }

    private fun setOnclick() {
        binding.textTab1.setOnClickListener {
            chooseTab1()
        }
        binding.textTab2.setOnClickListener {
            chooseTab2()
        }
    }

    override fun onResume() {
        super.onResume()

        chooseTab1()
        getUserCurrent()
        setUpView()
    }
}