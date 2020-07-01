package com.furkansubasiay.marveltestproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.furkansubasiay.marveltestproject.di.key.ViewModelKey
import com.furkansubasiay.marveltestproject.vm.CharacterDetailViewModel
import com.furkansubasiay.marveltestproject.vm.FavoritesViewModel
import com.furkansubasiay.marveltestproject.vm.CharactersViewModel
import com.furkansubasiay.marveltestproject.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    internal abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    internal abstract fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

}