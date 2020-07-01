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
