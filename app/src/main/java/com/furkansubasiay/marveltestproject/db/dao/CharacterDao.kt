package com.furkansubasiay.marveltestproject.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import androidx.room.FtsOptions.Order



/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Dao
interface CharacterDao {
    @Insert(onConflict = REPLACE)
    fun insert(vararg character: MarvelCharacter)

    @Update
    fun updateCharacter(vararg marvelCharacter: MarvelCharacter)

    @Query("UPDATE characters SET is_favorite=:isFavorite WHERE id=:characterId")
    fun updateCharacterById(isFavorite: Boolean, characterId: Long)

    @Query("DELETE FROM characters")
    fun deleteAll()

    @Query("SELECT * from characters")
    fun getAllCharacters() :MutableList<MarvelCharacter>

    @Query("SELECT COUNT(*) from characters  WHERE id=:id")
    fun getCharacterCountById(id:Long): Long

    @Query("SELECT * from characters where id==:characterId")
    fun getCharacterByID(characterId:Long):MarvelCharacter

    @Query("SELECT * from characters where is_favorite==:isFavorite order by uid")
    fun getFavoriteCharacters(isFavorite: Boolean):MutableList<MarvelCharacter>

}