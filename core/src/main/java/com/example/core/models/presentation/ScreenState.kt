package com.example.core.models.presentation

sealed interface ScreenState<out T> {
    data object Loading : ScreenState<Nothing>

    @JvmInline
    value class Error(val error: String) : ScreenState<Nothing>

    @JvmInline
    value class Success<T>(val data: T) : ScreenState<T>
}