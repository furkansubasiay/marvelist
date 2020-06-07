package com.furkansubasiay.marveltestproject.model.comics

import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.Nullable

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

data class ComicsResponse(
    @SerializedName("code") var code:Int,
    @SerializedName("status") var status:String,
    @Nullable@SerializedName("data") var comics_data: ComicsData
)

data class ComicsData(
    @SerializedName("offset") var offset: Int,
    @SerializedName("limit") var limit: Int,
    @SerializedName("total") var total: Int,
    @SerializedName("count")var count:Int,
    @SerializedName("results")var results:List<ComicsItem>
)