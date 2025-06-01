package com.example.feature.transfer.models.presentation

import com.example.core.accountsrepo.models.Account

sealed interface TransferEvent {

    @JvmInline
    value class OnFromAccountSet(val account: Account) : TransferEvent

    @JvmInline
    value class OnToAccountSet(val account: Account) : TransferEvent

    @JvmInline
    value class OnAmountSet(val amount: String) : TransferEvent

    @JvmInline
    value class OnFromAccountExpandedChange(val expanded: Boolean) : TransferEvent

    @JvmInline
    value class OnToAccountExpandedChange(val expanded: Boolean) : TransferEvent

    data class OnSubmitButtonClicked(
        val accountFromId: String,
        val accountToId: String,
        val amount: String
    ) : TransferEvent
}