package com.example.feature.transfer.models.presentation

import com.example.core.accountsrepo.models.Account

data class TransferScreenState (
    val fromAccountExpanded: Boolean,
    val toAccountExpanded: Boolean,
    val fromAccount: Account?,
    val toAccount: Account?,
    val amount: String?,
    val accountOptions: List<Account>
)