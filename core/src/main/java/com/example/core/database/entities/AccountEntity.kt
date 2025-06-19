package com.example.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.core.accountsrepo.models.Account
import com.example.core.database.Converters
import java.math.BigInteger

@Entity(
    tableName = "accounts",
    indices = [
        Index("number", unique = true)
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val number: String,
    val balance: BigInteger
) {
    fun toAccount() = Account(
        id = id,
        name = name,
        number = number,
        balance = balance
    )

    companion object {
        fun fromAccount(account: Account) = AccountEntity(
            id = 0,
            name = account.name,
            number = account.number,
            balance = account.balance
        )
    }
}
