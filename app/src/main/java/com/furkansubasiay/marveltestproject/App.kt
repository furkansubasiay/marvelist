package com.furkansubasiay.marveltestproject

import android.app.Activity
import com.furkansubasiay.marveltestproject.di.component.DaggerAppComponent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

class App :DaggerApplication() {

    var LIMIT = 30
    var OFFSET = 0
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initAnalytics()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)

    }

    fun initAnalytics() {
        firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
    }
}