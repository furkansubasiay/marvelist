package com.furkansubasiay.marveltestproject.network

import com.furkansubasiay.marveltestproject.BuildConfig
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Response
/**
 * Created by FURKAN SUBAŞIAY on 2020-06-05.
 */
/**
 * this is just a copy-paste class.
 * reference :
 * https://github.com/muratcanbur/ProjectX/
 */
@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter(TS_QUERY, BuildConfig.TS_VALUE)
            .addQueryParameter(API_HASH_QUERY, BuildConfig.API_HASH_VALUE)
            .addQueryParameter(API_KEY_QUERY, BuildConfig.API_KEY_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val TS_QUERY="ts"
        const val API_HASH_QUERY="hash"
        const val API_KEY_QUERY = "apikey"
    }
}