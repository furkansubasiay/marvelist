package com.furkansubasiay.marveltestproject.ui.detail

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.furkansubasiay.marveltestproject.db.MarvelDatabase
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import com.furkansubasiay.marveltestproject.model.comics.ComicsData
import com.furkansubasiay.marveltestproject.network.Repository
import com.furkansubasiay.marveltestproject.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
class CharacterDetailViewModel @Inject constructor(
    private val repos: Repository,
    val db: MarvelDatabase
) : BaseViewModel() {

    var characterId: Long = -1L

    private var disposable: CompositeDisposable? = null

    private val resultsLocalLiveData = MutableLiveData<MarvelCharacter>()
    private val resultsComicsLiveData = MutableLiveData<ComicsData>()


    val resultsComics: LiveData<ComicsData>?
        get() = resultsComicsLiveData


    val resultsLocalData: LiveData<MarvelCharacter>?
        get() = resultsLocalLiveData

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>?
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>?
        get() = _description

    private val _imgUrl = MutableLiveData<String>()
    val characterImage: LiveData<String>?
        get() = _imgUrl

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>?
        get() = _isFavorite

    init {
        disposable = CompositeDisposable()
    }

    //#### Karakter id si ile lokal db'den karakter çekilip gösterim yapıldı.####
    fun bindCharacterId(characterId: Long?) {
        this.characterId = characterId!!
        getCharacter(this.characterId)
        fetchComics()
    }

    private fun getCharacter(characterId: Long) {
        resultsLocalLiveData.value = repos.fetchCharacterDetailById(characterId)
        fillData()
    }

    private fun fillData() {
        if (resultsLocalLiveData.value != null) {
            _title.value = resultsLocalLiveData.value!!.name
            _description.value = resultsLocalLiveData.value!!.description
            _imgUrl.value = resultsLocalLiveData.value!!.character_img_url
            _isFavorite.value =resultsLocalLiveData.value!!.is_favorite
        }
    }

    //##### Comics ###########
    private fun fetchComics() {
        disposable!!.add(repos.fetchComics(characterId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result != null) {
                        resultsComicsLiveData.value = result.comics_data
                    }
                },
                { error ->
                    Log.e(TAG, "ERR= " + error.localizedMessage)
                }
            ))

    }

    //########################

    fun bindLikeButtonValue(isFavorite: Boolean) {
        repos.updateCharacterById(isFavorite,characterId)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear();
            disposable = null;
        }
    }


    companion object {
        const val TAG = "fetchComics_ERROR"
        @JvmStatic
        @BindingAdapter("characterImage")
        fun loadImage(view: ImageView, characterImage: String) {
            Glide.with(view.context)
                .load(characterImage)
                .into(view)
        }
    }
}