package com.example.search.exception

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.example.search.R
import com.google.android.material.snackbar.Snackbar
import com.example.search.exception.NetworkStateBroadcastReceiver.Companion.IS_NETWORK_AVAILABLE
import com.example.search.exception.NetworkStateBroadcastReceiver.Companion.NETWORK_AVAILABLE_ACTION


class NetworkStateHelper(private val context: Context,
                         private val snackBarContainer: View,
                         lifecycleOwner: LifecycleOwner,
                         stringId: Int = R.string.network_false,
                         duration: Int = Snackbar.LENGTH_INDEFINITE,
                         private val callback: ((Boolean) -> Unit)? = null)
    : LifecycleObserver {

    private val networkChangeReceiver = NetworkStateBroadcastReceiver()

    private val snackBar by lazy {
        Snackbar.make(snackBarContainer, context.getString(stringId), duration)
    }

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        registerNetworkListener()
    }

    private fun registerNetworkListener() {
        val appIntentFilter = IntentFilter().apply { addAction(ConnectivityManager.CONNECTIVITY_ACTION) }
        context.registerReceiver(networkChangeReceiver, appIntentFilter)
        val networkIntentFilter = IntentFilter(NETWORK_AVAILABLE_ACTION)

        //BR receive part
        context.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent) {
                val isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false)
                callback?.invoke(isNetworkAvailable)
                snackBar.apply {
                    if (isNetworkAvailable){
                        dismiss()
                    } else {
                        Log.e("Network State", isNetworkAvailable.toString())
                        show()
                    }
                }
            }
        }, networkIntentFilter)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disconnectListener() = context.unregisterReceiver(networkChangeReceiver)

}