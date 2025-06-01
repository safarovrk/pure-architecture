package com.example.purearchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.feature.navigator.MainNavigator
import com.example.feature.success.presentation.SuccessFragment
import com.example.feature.transfer.presentation.TransferFragment

class MainActivity : AppCompatActivity(), MainNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empty_activity)

        supportFragmentManager.commit {
            replace(
                R.id.container,
                TransferFragment(),
                TransferFragment.TAG
            )
        }
    }

    override fun openSuccessScreen(accountFromId: String, accountToId: String, amount: String) {
        supportFragmentManager.commit {
            replace(
                R.id.container,
                SuccessFragment.newInstance(
                    accountFromId = accountFromId,
                    accountToId = accountToId,
                    amount = amount
                ),
                SuccessFragment.TAG
            )
            addToBackStack(SuccessFragment.TAG)
        }
    }
}