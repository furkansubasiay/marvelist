package com.furkansubasiay.marveltestproject.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furkansubasiay.marveltestproject.App
import com.furkansubasiay.marveltestproject.db.MarvelDatabase
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import com.furkansubasiay.marveltestproject.model.character.CharacterData
import com.furkansubasiay.marveltestproject.model.character.MarvelCharacterItem
import com.furkansubasiay.marveltestproject.network.Repository
import com.furkansubasiay.marveltestproject.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
class CharactersViewModel    @Inject constructor(
        private val repos: Repository,
        val db: MarvelDatabase,
        val app: App
    ) : BaseViewModel() {
        private var disposable: CompositeDisposable? = null
        private val resultsLiveData = MutableLiveData<CharacterData>()

        init {
            disposable = CompositeDisposable()
            fetchRepos()
        }

        val results: LiveData<CharacterData>?
        get() = resultsLiveData


    var pagingListenerCallback: PagingListenerCallback? = null
    fun fetchReposPaging(pagingListenerCallback: PagingListenerCallback) {

        app.OFFSET += app.LIMIT
        this.pagingListenerCallback = pagingListenerCallback
        fetchRepos()
    }

    fun fetchRepos() {


        disposable!!.add(repos.fetchCharacters(app.LIMIT, app.OFFSET).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null) {


                        if (pagingListenerCallback != null) {
                            pagingListenerCallback!!.onMoreListenerFinished()
                        }

                        resultsLiveData.value = result.character_data
                        addCharactersResponseToLocale(resultsLiveData.value!!.results)

                    }
                },
                { error ->
                    if (pagingListenerCallback != null) {
                        pagingListenerCallback!!.onMoreListenerFinished()
                    }
                }
            ))
    }


    fun addCharactersResponseToLocale(characterResult: List<MarvelCharacterItem>) {

        if (characterResult.size > 0) {
            characterResult.forEach { characterItem ->
                val characterEntity = MarvelCharacter(
                    characterItem.character_id,
                    characterItem.name,
                    characterItem.description,
                    String.format(
                        "%s.%s",
                        characterItem.thumbnail.path,
                        characterItem.thumbnail.extension
                    ),
                    false
                )
                if (repos.getCountById(characterItem.character_id) > 0) {
                    characterEntity.is_favorite = repos.fetchCharacterDetailById(characterItem.character_id).is_favorite
                    repos.updateCharacter(characterEntity)
                } else {
                    repos.addCharacter(characterEntity)
                }


            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear();
            disposable = null;
        }
    }

}