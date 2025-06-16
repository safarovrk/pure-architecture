package com.example.core.accountsrepo.domian

import com.example.core.accountsrepo.models.Account
import com.example.core.accountsrepo.models.TransferStatus
import java.math.BigInteger

interface AccountsInteractor {
    suspend fun getAccounts(): List<Account>

    suspend fun putAccounts(accounts: List<Account>)

    suspend fun putAccount(account: Account)

    suspend fun transfer(fromAccountId: Long, toAccountId: Long, amount: BigInteger): TransferStatus
}
