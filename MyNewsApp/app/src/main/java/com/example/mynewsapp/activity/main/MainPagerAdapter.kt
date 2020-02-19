package com.example.mynewsapp.activity.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mynewsapp.R
import com.example.mynewsapp.activity.main.pages.TopNewsFragment
import com.example.mynewsapp.activity.main.pages.TopNewsViewModel

private const val PAGE_COUNT = 3

class MainPagerAdapter(
    private val context: Context, fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopNewsFragment.newInstance()
            else -> TopNewsFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getStringArray(R.array.page_titles)[position]
    }

}