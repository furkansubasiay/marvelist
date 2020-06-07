package com.furkansubasiay.marveltestproject.di.module.fragmentmodule


import com.furkansubasiay.marveltestproject.ui.favoriteslist.FavoritesListFragment
import dagger.Module
import dagger.Provides

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
class FavoritesListFragmentModule {

    @Provides
    fun provideFragmentName(favoritesListFragment: FavoritesListFragment): String {
        return favoritesListFragment.javaClass.name
    }
}