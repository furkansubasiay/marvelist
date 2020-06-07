package com.furkansubasiay.marveltestproject.di.key

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
annotation class ActivityKey(val value: KClass<out Activity>)