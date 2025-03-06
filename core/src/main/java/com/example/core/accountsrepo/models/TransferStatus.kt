package com.example.core.accountsrepo.models

sealed interface TransferStatus {
    data object Success : TransferStatus

    @JvmInline
    value class Error(val message: String) : TransferStatus
}