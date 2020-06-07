package com.furkansubasiay.marveltestproject.di.module

import com.furkansubasiay.marveltestproject.di.module.fragmentmodule.CharacterDetailFragmentModule
import com.furkansubasiay.marveltestproject.di.module.fragmentmodule.CharacterListFragmentModule
import com.furkansubasiay.marveltestproject.di.module.fragmentmodule.FavoritesListFragmentModule
import com.furkansubasiay.marveltestproject.di.scope.FragmentScope
import com.furkansubasiay.marveltestproject.ui.detail.CharacterDetailFragment
import com.furkansubasiay.marveltestproject.ui.favoriteslist.FavoritesListFragment
import com.furkansubasiay.marveltestproject.ui.herolist.CharacterListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CharacterListFragmentModule::class])
    abstract fun bindCharacterListFragment(): CharacterListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CharacterDetailFragmentModule::class])
    abstract fun bindCharacterDetailFragment(): CharacterDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavoritesListFragmentModule::class])
    abstract fun bindFavoriteListFragment(): FavoritesListFragment
}