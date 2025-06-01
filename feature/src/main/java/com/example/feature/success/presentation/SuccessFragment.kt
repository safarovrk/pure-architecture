package com.example.feature.success.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SuccessFragment : Fragment() {

    private val accountFromName: String? by lazy { requireArguments().getString(ACCOUNT_TO_KEY) }
    private val accountToName: String? by lazy { requireArguments().getString(ACCOUNT_FROM_KEY) }
    private val amount: String? by lazy { requireArguments().getString(AMOUNT_KEY) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Text(text = accountFromName.orEmpty())
                        Text(text = accountToName.orEmpty())
                        Text(text = amount.orEmpty())
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "TransferFragment"
        private const val ACCOUNT_TO_KEY = "account_to_key"
        private const val ACCOUNT_FROM_KEY = "account_from_key"
        private const val AMOUNT_KEY = "amount_key"

        fun newInstance(accountFromId: String, accountToId: String, amount: String) =
            SuccessFragment().apply {
                arguments = bundleOf(
                    ACCOUNT_TO_KEY to accountFromId,
                    ACCOUNT_FROM_KEY to accountToId,
                    AMOUNT_KEY to amount,
                )
            }
    }
}