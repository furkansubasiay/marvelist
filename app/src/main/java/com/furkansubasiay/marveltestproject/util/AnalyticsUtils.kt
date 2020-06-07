package com.furkansubasiay.marveltestproject.util

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-07.
 */

class AnalyticsUtils {

    private   var BUNDLE_CHARACTER_ID = "character_id"

    private    var BUNDLE_SCREEN_VIEW = "screen_view"

    fun getFirebaseAnalytics(context: Context): FirebaseAnalytics {
        return  FirebaseAnalytics.getInstance(context)
    }

    fun logScreenViews(context: Context,screenNameValue:  SCREEN_NAMES)
    {
        logScreenViews(context,screenNameValue,0)
    }

    fun logScreenViews(context: Context,screenNameValue:  SCREEN_NAMES, characterId: Long)
    {
        val bundle = Bundle()
        bundle.putLong(BUNDLE_CHARACTER_ID, characterId)
        Log.e("SCREEN_NAME",BUNDLE_SCREEN_VIEW +   "_" +screenNameValue.get.replace(" ",""))
        getFirebaseAnalytics(context).logEvent(BUNDLE_SCREEN_VIEW +   "_" +screenNameValue.get.replace(" ",""),bundle)
    }

    private  fun screenNameValueOf(value: String): SCREEN_NAMES{
        return  SCREEN_NAMES.values().find { it.get == value }!!
    }

    enum  class  SCREEN_NAMES (var get: String) {
        marvelist("Marvelist"),
        favoritelist("FavoriteList"),
        detail("CharacterDetail")
    }
}