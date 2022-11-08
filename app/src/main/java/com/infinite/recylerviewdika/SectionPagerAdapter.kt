package com.infinite.recylerviewdika

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.infinite.recylerviewdika.presentation.CardFragment
import com.infinite.recylerviewdika.presentation.GridFragment
import com.infinite.recylerviewdika.presentation.ListFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ListFragment()
            1 -> fragment = GridFragment()
            2 -> fragment = CardFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }

}