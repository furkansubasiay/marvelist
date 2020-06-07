package com.furkansubasiay.marveltestproject.ui.tab


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.ui.favoriteslist.FavoritesListFragment
import com.furkansubasiay.marveltestproject.ui.herolist.CharacterListFragment
import com.google.android.material.tabs.TabLayout


class TabFragment : Fragment() {
    internal var adapter: ViewPagerAdapter?=null
    internal var viewPager: ViewPager?=null
    var tabLayout:TabLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter!!.addFrag ( CharacterListFragment(),getString(R.string.tab_title_marvelist)) //index 0
        adapter!!.addFrag ( FavoritesListFragment(),getString(R.string.tab_title_favorites)) //index 1
        viewPager=view.findViewById(R.id.view_pager)
        viewPager!!.adapter=adapter
        tabLayout=view.findViewById(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)
        return view
    }


}
