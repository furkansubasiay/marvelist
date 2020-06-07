package com.furkansubasiay.marveltestproject.di.module.fragmentmodule

import com.furkansubasiay.marveltestproject.di.scope.FragmentScope
import com.furkansubasiay.marveltestproject.network.MarvelService
import com.furkansubasiay.marveltestproject.ui.detail.CharacterDetailFragment
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
class CharacterDetailFragmentModule {

    @FragmentScope
    @Provides
    fun provideMarvelService(retrofit: Retrofit): MarvelService = retrofit.create(MarvelService::class.java)

    @Provides
    fun provideActivityName(characterDetailFragment: CharacterDetailFragment): String {
        return characterDetailFragment.javaClass.name
    }
}