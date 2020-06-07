package com.furkansubasiay.marveltestproject.di.module

import androidx.room.Room
import com.furkansubasiay.marveltestproject.App
import com.furkansubasiay.marveltestproject.db.MarvelDatabase
import com.furkansubasiay.marveltestproject.db.dao.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: App): MarvelDatabase {
        return Room.databaseBuilder(app, MarvelDatabase::class.java, "marvel.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(marvelDatabase: MarvelDatabase): CharacterDao = marvelDatabase.characterDao()

}