package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.database.entities.AccountEntity

@Database(
    version = 1,
    entities = [
        AccountEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

}