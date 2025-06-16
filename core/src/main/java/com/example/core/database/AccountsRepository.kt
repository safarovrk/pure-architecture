package com.example.core.database

import com.example.core.accountsrepo.models.Account
import com.example.core.database.entities.AccountEntity
import java.math.BigInteger
import javax.inject.Inject

class AccountsRepository @Inject constructor(
    private val accountsDao: AccountsDao
) {
    suspend fun getAccountById(id: Long): Account? =
        accountsDao.getAccountById(id)?.toAccount()

    suspend fun getAccounts(): List<Account> =
        accountsDao.getAllAccounts().map { account -> account.toAccount() }

    suspend fun updateBalanceById(id: Long, newBalance: BigInteger): Boolean {
        val account = getAccountById(id)
        if (account != null) {
            accountsDao.updateAccount(
                account = account.copy(balance = newBalance)
            )
            return true
        } else {
            return false
        }
    }

    suspend fun putAccounts(accounts: List<Account>) =
        accounts.forEach { account ->
            accountsDao.createAccount(AccountEntity.fromAccount(account))
        }


    suspend fun putAccount(account: Account) =
        accountsDao.createAccount(AccountEntity.fromAccount(account))

}
