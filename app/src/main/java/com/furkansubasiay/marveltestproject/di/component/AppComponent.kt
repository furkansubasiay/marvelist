package com.furkansubasiay.marveltestproject.di.component

import com.furkansubasiay.marveltestproject.App
import com.furkansubasiay.marveltestproject.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    FragmentBuilderModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    DatabaseModule::class])

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>

}
