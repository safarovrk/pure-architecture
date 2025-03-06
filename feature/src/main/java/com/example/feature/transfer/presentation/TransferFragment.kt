package com.example.feature.transfer.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.core.accountsrepo.domian.AccountsInteractorImpl
import com.example.feature.navigator.MainNavigator
import com.example.feature.transfer.models.presentation.TransferEvent
import com.example.feature.transfer.presentation.compose.TransferScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TransferFragment : Fragment() {

    private val viewModel = TransferViewModel(AccountsInteractorImpl())

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, throwable.message.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(exceptionHandler) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is TransferEvent.OnSubmitButtonClicked -> onTransferSuccess(
                        accountFromId = event.accountFromId,
                        accountToId = event.accountToId,
                        amount = event.amount
                    )

                    else -> Unit
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                TransferScreen(viewModel)
            }
        }
    }

    private fun onTransferSuccess(accountFromId: String, accountToId: String, amount: String) {
        (requireActivity() as MainNavigator).openSuccessScreen(
            accountFromId = accountFromId,
            accountToId = accountToId,
            amount = amount
        )
    }

    companion object {
        const val TAG = "TransferFragment"
    }
}