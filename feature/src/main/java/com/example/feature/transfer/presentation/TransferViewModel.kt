package com.example.feature.transfer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.accountsrepo.domian.AccountsInteractor
import com.example.core.accountsrepo.models.TransferStatus
import com.example.core.models.presentation.ScreenState
import com.example.feature.transfer.models.presentation.TransferEvent
import com.example.feature.transfer.models.presentation.TransferScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransferViewModel(
    private val accountsInteractor: AccountsInteractor
) : ViewModel() {
    private val _uiState: MutableStateFlow<ScreenState<TransferScreenState>> =
        MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState<TransferScreenState>> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<TransferEvent>()
    val uiEvent: SharedFlow<TransferEvent> = _uiEvent.asSharedFlow()

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        delay(2000)
        val accounts = accountsInteractor.getAccounts()
        _uiState.update {
            ScreenState.Success(
                TransferScreenState(
                    fromAccountExpanded = false,
                    toAccountExpanded = false,
                    fromAccount = accounts.getOrNull(1),
                    toAccount = accounts.getOrNull(2),
                    amount = "",
                    accountOptions = accounts
                )
            )
        }
    }

    private fun updateState(state: TransferScreenState) =
        _uiState.update { ScreenState.Success(state) }

    private fun getSuccessState() = (uiState.value as ScreenState.Success).data

    fun onEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.OnFromAccountExpandedChange -> {
                updateState(getSuccessState().copy(fromAccountExpanded = event.expanded))
            }

            is TransferEvent.OnFromAccountSet -> {
                updateState(getSuccessState().copy(fromAccount = event.account))
            }

            is TransferEvent.OnToAccountExpandedChange -> {
                updateState(getSuccessState().copy(toAccountExpanded = event.expanded))
            }

            is TransferEvent.OnToAccountSet -> {
                updateState(getSuccessState().copy(toAccount = event.account))
            }

            is TransferEvent.OnAmountSet -> {
                if (event.amount.all { char -> char.isDigit() || char == '.' }) {
                    updateState(getSuccessState().copy(amount = event.amount))
                }
            }

            is TransferEvent.OnSubmitButtonClicked -> viewModelScope.launch {
                _uiState.update { ScreenState.Loading }
                delay(2000)
                val transferResult = accountsInteractor.transfer(
                    fromAccountId = event.accountFromId,
                    toAccountId = event.accountToId,
                    amount = event.amount.toBigInteger()
                )
                when (transferResult) {
                    is TransferStatus.Error -> _uiState.update { ScreenState.Error(transferResult.message) }
                    TransferStatus.Success -> _uiEvent.emit(event)
                }
            }
        }
    }
}