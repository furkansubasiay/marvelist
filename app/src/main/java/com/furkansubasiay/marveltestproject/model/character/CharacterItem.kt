package com.furkansubasiay.marveltestproject.model.character

import com.google.gson.annotations.SerializedName

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

data class MarvelCharacterItem(
    @SerializedName( "id") val character_id:Long,
    @SerializedName( "name") val name:String,
     @SerializedName("description") val description:String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
)


data class Thumbnail(
    @SerializedName("path")val path:String,
    @SerializedName("extension") val extension:String
)
{
    override fun toString(): String {
        return  String.format("%s.%s",path,extension)
    }
}



/*"thumbnail": {
    "path": "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
    "extension": "jpg"
}*/