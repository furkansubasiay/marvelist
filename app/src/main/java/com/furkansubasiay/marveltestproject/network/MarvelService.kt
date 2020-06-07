package com.furkansubasiay.marveltestproject.network

import com.furkansubasiay.marveltestproject.model.character.CharacterResponse
import com.furkansubasiay.marveltestproject.model.comics.ComicsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
interface MarvelService {
    @GET("characters")
    fun getCharacters(@Query ("limit") limit:Int,@Query("offset") offset:Int): Single<CharacterResponse>

    @GET("characters/{characterId}/comics")
    fun getComics(@Path("characterId")characterId:Long,@Query ("dateRange") dateRange:String, @Query("orderBy") orderBy:String,@Query("limit") limit:Int): Single<ComicsResponse>
}


/*

Characters
http://gateway.marvel.com/v1/public/characters/1011334?ts=1&apikey=b5d580c75ec8852209acef213d4c6d11&hash=ec5e60e062f0c80fcfa0651118d65350


Comics
https://gateway.marvel.com:443/v1/public/characters/1011198/comics?dateRange=2008-01-01%2C2021-01-01&orderBy=-onsaleDate&limit=10&ts=1&apikey=b5d580c75ec8852209acef213d4c6d11&hash=ec5e60e062f0c80fcfa0651118d65350

 */