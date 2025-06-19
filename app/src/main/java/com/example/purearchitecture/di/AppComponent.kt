package com.example.purearchitecture.di

import android.app.Application
import android.content.Context
import com.example.core.di.CoreModule
import com.example.feature.di.FeatureModule
import com.example.feature.transfer.presentation.TransferFragment
import com.example.purearchitecture.MainActivity
import com.example.purearchitecture.TheApplication
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CoreModule::class,
        FeatureModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class
    ]
)
interface AppComponent {
    fun inject(app: TheApplication)
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindTransferFragment(): TransferFragment
}

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}