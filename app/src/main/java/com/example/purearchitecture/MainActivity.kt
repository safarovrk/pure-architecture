package com.example.purearchitecture

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.feature.navigator.MainNavigator
import com.example.feature.success.presentation.SuccessFragment
import com.example.feature.transfer.presentation.TransferFragment
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity

class MainActivity : AppCompatActivity(), MainNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.empty_activity)
        AndroidInjection.inject(this)
        supportFragmentManager.commit {
            replace(
                R.id.container,
                TransferFragment(),
                TransferFragment.TAG
            )
        }
    }

    override fun openSuccessScreen(accountFromId: Long, accountToId: Long, amount: String) {
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