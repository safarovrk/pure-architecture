package com.example.core.accountsrepo.domian

import com.example.core.accountsrepo.data.AccountsStorage
import com.example.core.accountsrepo.models.Account
import com.example.core.accountsrepo.models.TransferStatus
import java.math.BigInteger

class AccountsInteractorImpl : AccountsInteractor {
    override fun getAccounts(): List<Account> {
        return AccountsStorage.accounts
    }

    override fun transfer(
        fromAccountId: String,
        toAccountId: String,
        amount: BigInteger
    ): TransferStatus {
        val fromAccount = AccountsStorage.accounts.find { account -> account.id == fromAccountId }
        val toAccount = AccountsStorage.accounts.find { account -> account.id == toAccountId }
        if (fromAccount == null || toAccount == null) {
            return TransferStatus.Error(ERROR_NOT_EXIST)
        }
        if (fromAccount.balance < amount) {
            return TransferStatus.Error(ERROR_NOT_ENOUGH)
        } else {
            AccountsStorage.accounts.replaceAll { account ->
                if (account.id == fromAccountId) {
                    account.copy(balance = account.balance - amount)
                } else account
            }
            AccountsStorage.accounts.replaceAll { account ->
                if (account.id == toAccountId) {
                    account.copy(balance = account.balance + amount)
                } else account
            }
            return TransferStatus.Success
        }
    }

    companion object {
        private const val ERROR_NOT_EXIST = "Не удается найти указанные счета"
        private const val ERROR_NOT_ENOUGH = "Недостаточно средств на счете"
    }
}