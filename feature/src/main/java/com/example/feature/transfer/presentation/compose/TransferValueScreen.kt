package com.example.feature.transfer.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.feature.R
import com.example.feature.transfer.models.presentation.TransferEvent
import com.example.feature.transfer.models.presentation.TransferScreenState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferValueScreen(state: TransferScreenState, onEvent: (TransferEvent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = state.fromAccountExpanded,
            onExpandedChange = { expanded ->
                onEvent(TransferEvent.OnFromAccountExpandedChange(expanded))
            }
        ) {
            TextField(
                value = state.fromAccount?.name.orEmpty() +
                        SEPARATOR +
                        state.fromAccount?.balance +
                        stringResource(R.string.rub) +
                        SEPARATOR +
                        stringResource(R.string.dotted_number, state.fromAccount?.number.orEmpty()),
                onValueChange = { },
                label = { Text(stringResource(R.string.from_account)) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                    .fillMaxWidth(),
                readOnly = true,
                leadingIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "From account"
                    )
                },
                trailingIcon = { TrailingIcon(expanded = state.fromAccountExpanded) },
            )
            ExposedDropdownMenu(
                expanded = state.fromAccountExpanded,
                onDismissRequest = { onEvent(TransferEvent.OnFromAccountExpandedChange(false)) }
            ) {
                state.accountOptions.forEach { account ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = account.name +
                                        SEPARATOR +
                                        account.balance +
                                        stringResource(R.string.rub) +
                                        SEPARATOR +
                                        stringResource(R.string.dotted_number, account.number)
                            )
                        },
                        onClick = {
                            onEvent(TransferEvent.OnFromAccountSet(account))
                            onEvent(TransferEvent.OnFromAccountExpandedChange(false))
                        }
                    )
                }
            }
        }

        ExposedDropdownMenuBox(
            expanded = state.toAccountExpanded,
            onExpandedChange = { expanded ->
                onEvent(
                    TransferEvent.OnToAccountExpandedChange(
                        expanded
                    )
                )
            }
        ) {
            TextField(
                value = state.toAccount?.name.orEmpty() +
                        SEPARATOR +
                        state.toAccount?.balance +
                        stringResource(R.string.rub) +
                        SEPARATOR +
                        stringResource(R.string.dotted_number, state.toAccount?.number.orEmpty()),
                onValueChange = { },
                label = { Text(stringResource(R.string.to_account)) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                    .fillMaxWidth(),
                readOnly = true,
                leadingIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "To account"
                    )
                },
                trailingIcon = { TrailingIcon(expanded = state.toAccountExpanded) },
            )
            ExposedDropdownMenu(
                expanded = state.toAccountExpanded,
                onDismissRequest = { onEvent(TransferEvent.OnToAccountExpandedChange(false)) }
            ) {
                state.accountOptions.forEach { account ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = account.name +
                                        SEPARATOR +
                                        account.balance +
                                        stringResource(R.string.rub) +
                                        SEPARATOR +
                                        stringResource(R.string.dotted_number, account.number)
                            )
                        },
                        onClick = {
                            onEvent(TransferEvent.OnToAccountSet(account))
                            onEvent(TransferEvent.OnToAccountExpandedChange(false))
                        }
                    )
                }
            }
        }

        Row {
            OutlinedTextField(
                value = state.amount.orEmpty(),
                onValueChange = { amount -> onEvent(TransferEvent.OnAmountSet(amount)) },
                label = { Text(stringResource(R.string.amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(R.string.rub)
            )
        }

        Button(
            onClick = {
                onEvent(
                    TransferEvent.OnSubmitButtonClicked(
                        accountFromId = state.fromAccount?.id ?: 0L,
                        accountToId = state.toAccount?.id ?: 0L,
                        amount = state.amount.orEmpty()
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = state.fromAccount?.name?.isNotBlank() == true &&
                    state.toAccount?.name?.isNotBlank() == true &&
                    state.amount?.isNotBlank() == true
        ) {
            Text(stringResource(R.string.transfer))
        }
    }
}

private const val SEPARATOR: String = " — "