package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.accountsrepo.domian.AccountsInteractor
import com.example.core.accountsrepo.domian.AccountsInteractorImpl
import com.example.core.database.AccountsDao
import com.example.core.database.AccountsRepository
import com.example.core.database.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database.db")
            .build()
    }

    @Provides
    fun provideAccountsDao(db: AppDatabase): AccountsDao {
        return db.getAccountsDao()
    }

    @Provides
    @Singleton
    fun provideAccountsRepository(accountsDao: AccountsDao): AccountsRepository {
        return AccountsRepository(accountsDao)
    }

    @Provides
    fun provideAccountsInteractor(accountsRepository: AccountsRepository): AccountsInteractor {
        return AccountsInteractorImpl(accountsRepository)
    }
}