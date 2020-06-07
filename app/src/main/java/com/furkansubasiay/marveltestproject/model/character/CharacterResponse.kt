package com.furkansubasiay.marveltestproject.model.character

import com.google.gson.annotations.SerializedName

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

data class CharacterResponse(
    @SerializedName("code") var code:Int,
    @SerializedName("status") var status:String,
    @SerializedName("data") var character_data: CharacterData
)

data class CharacterData(
    @SerializedName("offset") var offset: Int,
    @SerializedName("limit") var limit: Int,
    @SerializedName("total") var total: Int,
    @SerializedName("count")var count:Int,
    @SerializedName("results")var results:List<MarvelCharacterItem>
)

