package com.furkansubasiay.marveltestproject.ui

import android.os.Bundle
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.ui.tab.TabFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = TabFragment()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, mainFragment)
            .commit()
    }
}
