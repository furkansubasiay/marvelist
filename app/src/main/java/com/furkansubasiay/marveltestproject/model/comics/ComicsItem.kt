package com.furkansubasiay.marveltestproject.model.comics

import com.google.gson.annotations.SerializedName

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
data class ComicsItem(
    @SerializedName( "id") val comics_id:Long,
    @SerializedName( "title") val title:String,
    @SerializedName("thumbnail") val comics_thumbnail: ComicsThumbnail
)

data class ComicsThumbnail(
    @SerializedName("path")val path:String,
    @SerializedName("extension") val extension:String

)