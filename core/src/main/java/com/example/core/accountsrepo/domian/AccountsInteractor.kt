package com.example.core.accountsrepo.domian

import com.example.core.accountsrepo.models.Account
import com.example.core.accountsrepo.models.TransferStatus
import java.math.BigInteger

interface AccountsInteractor {
    fun getAccounts(): List<Account>

    fun transfer(fromAccountId: String, toAccountId: String, amount: BigInteger): TransferStatus
}
