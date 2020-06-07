package com.furkansubasiay.marveltestproject.di.module

import com.furkansubasiay.marveltestproject.di.scope.ActivityScope
import com.furkansubasiay.marveltestproject.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun bindActivity(): MainActivity

}