package com.fadhil.mygithubapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fadhil.mygithubapp.ui.detail.follower.FollowerFragment

class SectionsPagerAdapter(activity: AppCompatActivity, val username: String) :
    FragmentStateAdapter(activity) {


    override fun createFragment(position: Int): Fragment {

        val fragment = FollowerFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowerFragment.ARG_SECTION_NUMBER,position.plus(1))
            putString(FollowerFragment.ARG_SECTION_USERNAME,username)
        }







        return fragment



    }

    

    override fun getItemCount(): Int {
        return 2
    }
}


