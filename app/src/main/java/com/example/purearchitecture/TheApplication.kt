package com.example.purearchitecture

import android.app.Application
import android.content.Context
import com.example.purearchitecture.di.AppComponent
import com.example.purearchitecture.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class TheApplication : Application(), HasAndroidInjector {
    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector() = androidInjector
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is TheApplication -> appComponent
        else -> this.applicationContext.appComponent
    }