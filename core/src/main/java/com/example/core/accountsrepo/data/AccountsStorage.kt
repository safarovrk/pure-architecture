package com.example.core.accountsrepo.data

import com.example.core.accountsrepo.models.Account
import java.math.BigInteger

object AccountsStorage {
    val accounts: MutableList<Account> = mutableListOf(
        Account(
            id = 1L,
            name = "Счет1",
            number = "1111",
            balance = BigInteger("1000")
        ),
        Account(
            id = 2L,
            name = "Счет2",
            number = "2222",
            balance = BigInteger("2000")
        ),
        Account(
            id = 3L,
            name = "Счет3",
            number = "3333",
            balance = BigInteger("3000")
        ),
        Account(
            id = 4L,
            name = "Счет4",
            number = "4444",
            balance = BigInteger("4000")
        ),
        Account(
            id = 5L,
            name = "Счет5",
            number = "5555",
            balance = BigInteger("5000")
        ),
        Account(
            id = 6L,
            name = "Счет6",
            number = "6666",
            balance = BigInteger("6000")
        ),
        Account(
            id = 7L,
            name = "Счет7",
            number = "7777",
            balance = BigInteger("7000")
        )
    )
}