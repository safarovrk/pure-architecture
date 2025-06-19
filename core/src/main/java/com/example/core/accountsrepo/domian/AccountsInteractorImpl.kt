package com.example.core.accountsrepo.domian

import com.example.core.accountsrepo.models.Account
import com.example.core.accountsrepo.models.TransferStatus
import com.example.core.database.AccountsRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.math.BigInteger
import javax.inject.Inject

class AccountsInteractorImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
) : AccountsInteractor {

    private val transactionMutex = Mutex()

    override suspend fun getAccounts(): List<Account> = transactionMutex.withLock {
        return accountsRepository.getAccounts()
    }

    override suspend fun putAccounts(accounts: List<Account>) = transactionMutex.withLock {
        accountsRepository.putAccounts(accounts)
    }

    override suspend fun putAccount(account: Account) = transactionMutex.withLock {
        accountsRepository.putAccount(account)
    }

    override suspend fun transfer(
        fromAccountId: Long,
        toAccountId: Long,
        amount: BigInteger
    ): TransferStatus {

        if (fromAccountId == toAccountId) {
            return TransferStatus.Error(ERROR_SAME_ACCOUNT)
        }

        return transactionMutex.withLock {
            val fromAccount = accountsRepository.getAccountById(fromAccountId)
            val toAccount = accountsRepository.getAccountById(toAccountId)

            if (fromAccount == null || toAccount == null) {
                return@withLock TransferStatus.Error(ERROR_NOT_EXIST)
            }

            if (fromAccount.balance < amount) {
                return@withLock TransferStatus.Error(ERROR_NOT_ENOUGH)
            }

            try {
                accountsRepository.updateBalanceById(
                    id = fromAccount.id,
                    newBalance = fromAccount.balance - amount
                )
                accountsRepository.updateBalanceById(
                    id = toAccount.id,
                    newBalance = toAccount.balance + amount
                )
                return TransferStatus.Success
            } catch (e: Exception) {
                TransferStatus.Error("${ERROR_TRANSACTION_FAILED}: ${e.message}")
            }
        }
    }

    companion object {
        private const val ERROR_NOT_EXIST = "Не удается найти указанные счета"
        private const val ERROR_NOT_ENOUGH = "Недостаточно средств на счете"
        private const val ERROR_SAME_ACCOUNT = "Нельзя перевести на тот же счет"
        private const val ERROR_TRANSACTION_FAILED = "Ошибка при выполнении транзакции"
    }
}