package com.furkansubasiay.marveltestproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.furkansubasiay.marveltestproject.db.dao.CharacterDao
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Database(entities = arrayOf(MarvelCharacter::class),version = 1,exportSchema = false)
abstract class MarvelDatabase:RoomDatabase() {
    abstract fun characterDao():CharacterDao
}