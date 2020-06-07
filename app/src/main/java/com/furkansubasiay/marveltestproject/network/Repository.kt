package com.furkansubasiay.marveltestproject.network

import android.util.Log
import com.furkansubasiay.marveltestproject.db.MarvelDatabase
import com.furkansubasiay.marveltestproject.db.dao.CharacterDao
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
class Repository @Inject constructor(private val service: MarvelService,private val db :MarvelDatabase) {

    companion object{
        const val DATE_RANGE_VALUE="2006-01-01,now"
        const val ORDER_BY_VALUE="-onsaleDate"
        const val COMICS_LIMIT=10
    }

    fun fetchCharacters(limit:Int,offset:Int)=service.getCharacters(limit,offset)

    fun fetchComics(characterId: Long) = service.getComics(characterId,DATE_RANGE_VALUE,ORDER_BY_VALUE,COMICS_LIMIT)

    fun fetchCharacterDetailById(characterId:Long) = db.characterDao().getCharacterByID(characterId)

    fun addCharacter(marvelCharacter: MarvelCharacter) = db.characterDao().insert(marvelCharacter)

    fun updateCharacter(marvelCharacter: MarvelCharacter) = db.characterDao().updateCharacter(marvelCharacter)

    fun updateCharacterById(isFavorite:Boolean,characterId:Long) = db.characterDao().updateCharacterById(isFavorite,characterId)

    fun getCountById(characterId:Long) = db.characterDao().getCharacterCountById(characterId)

    fun getFavoriteCharacters() = db.characterDao().getFavoriteCharacters(true)

}