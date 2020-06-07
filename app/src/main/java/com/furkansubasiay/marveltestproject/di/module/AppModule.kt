package com.furkansubasiay.marveltestproject.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.furkansubasiay.marveltestproject.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

}