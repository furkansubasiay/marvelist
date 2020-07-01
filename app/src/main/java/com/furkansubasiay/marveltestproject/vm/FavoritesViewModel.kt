package com.furkansubasiay.marveltestproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkansubasiay.marveltestproject.App
import com.furkansubasiay.marveltestproject.db.MarvelDatabase
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import com.furkansubasiay.marveltestproject.network.Repository
import com.furkansubasiay.marveltestproject.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-07.
 */

class FavoritesViewModel @Inject constructor(
    private val repos: Repository,
    val db: MarvelDatabase,
    val app: App
) : BaseViewModel() {
        private var disposable: CompositeDisposable? = null
        private val resultsLiveData = MutableLiveData<MutableList<MarvelCharacter>>()

        init {
            disposable = CompositeDisposable()
            fetchFavorites()
        }

    val results: LiveData<MutableList<MarvelCharacter>>?
        get() = resultsLiveData

    //updateFavoriteList
    fun getFavorites(){
        fetchFavorites()
    }
    private fun fetchFavorites() {
       resultsLiveData.value= repos.getFavoriteCharacters()
    }



}