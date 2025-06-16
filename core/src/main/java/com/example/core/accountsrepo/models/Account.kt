package com.example.core.accountsrepo.models

import java.math.BigInteger

data class Account(
    val id: Long,
    val name: String,
    val number: String,
    val balance: BigInteger
)
