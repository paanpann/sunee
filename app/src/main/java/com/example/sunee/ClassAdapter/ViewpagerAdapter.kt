package com.example.sunee.ClassAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sunee.Fragment.Cooked_Rice
import com.example.sunee.Fragment.Glutinous_Rice

@Suppress("DEPRECATION")
class ViewpagerAdapter(manager :FragmentManager):FragmentPagerAdapter(manager) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> {return Cooked_Rice()}
            1-> {return Glutinous_Rice()}
            else ->{return Cooked_Rice()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 ->{return "ข้าวเหนียว"}
            1 ->{return "ข้าวหอมมะลิ"}
        }
     return super.getPageTitle(position)
    }
}