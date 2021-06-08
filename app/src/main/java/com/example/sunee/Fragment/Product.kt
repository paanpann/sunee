package com.example.sunee.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunee.ClassAdapter.ViewpagerAdapter
import com.example.sunee.Details_Cooked
import com.example.sunee.R
import com.example.sunee.Setting
import kotlinx.android.synthetic.main.fragment_product.*


class Product : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sutupViewpage()

        icon_setup_promotion.setOnClickListener {
            startActivity(Intent(requireContext(), Setting::class.java))
            onStart()
        }


    }

    private fun sutupViewpage() {
        val adapter = ViewpagerAdapter(childFragmentManager)
        ViewPager.adapter = adapter
        tabs.setupWithViewPager(ViewPager)
    }

}