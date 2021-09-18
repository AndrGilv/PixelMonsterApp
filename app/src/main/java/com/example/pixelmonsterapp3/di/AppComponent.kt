package com.example.pixelmonsterapp3.di

import android.content.Context
import com.example.pixelmonsterapp3.di.modules.AppModule
import com.example.pixelmonsterapp3.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App, @BindsInstance context: Context = app): AppComponent
    }

    override fun inject(application: App)
}
