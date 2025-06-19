package com.example.core.database

import androidx.room.TypeConverter
import java.math.BigInteger


object Converters {
    @TypeConverter
    fun fromString(value: String?): BigInteger? {
        return value?.let { value -> BigInteger(value) }
    }

    @TypeConverter
    fun toString(value: BigInteger?): String? {
        return value?.toString()
    }
}