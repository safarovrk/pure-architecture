package com.example.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.accountsrepo.models.Account
import com.example.core.database.entities.AccountEntity

@Dao
interface AccountsDao {

    @Update(entity = AccountEntity::class)
    suspend fun updateAccount(account: Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): List<AccountEntity>

    @Query("SELECT * FROM accounts WHERE id = :id")//возвращает список??
    fun getAccountById(id: Long): AccountEntity?

    @Query("DELETE FROM accounts")
    fun deleteAllAccounts()

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun deleteAccountById(id: Long)
}