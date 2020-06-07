package com.furkansubasiay.marveltestproject.di.module

import com.furkansubasiay.marveltestproject.di.scope.ActivityScope
import com.furkansubasiay.marveltestproject.network.MarvelService
import com.furkansubasiay.marveltestproject.ui.MainActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-05.
 */

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideMarvelService(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

    @Provides
    fun provideActivityName(mainActivity: MainActivity): String {
        return mainActivity.javaClass.name
    }
}