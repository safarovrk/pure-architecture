package com.example.core.accountsrepo.models

import java.math.BigInteger

data class Account(
    val id: String,
    val name: String,
    val number: String,
    val balance: BigInteger
)
