package com.example.search.exception

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * ConnectivityManager를 사용하여 인터넷에 연결되어 있는지 확인하고,
 * 연결되어 있다면 어떤 유형의 연결이 설정되어 있는지 확인할 수 있습니다.
 */
class NoConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn()) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }

    //인터넷 연결 설정 여부 결과 반환
    private fun isConnectionOn(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            postAndroidMInternetCheck(connectivityManager)
        } else {
            preAndroidMInternetCheck(connectivityManager)
        }
    }

    private fun preAndroidMInternetCheck(
            connectivityManager: ConnectivityManager
    ): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        //인터넷 연결이 되어 있다면 true 리턴
        if (activeNetwork != null && isConnected) {
            return (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }

    //Android 10(API 수준 29) 이상을 타겟팅하는 앱에는 NetworkCallbacks 를 대신 사용.
    @RequiresApi(Build.VERSION_CODES.M)
    private fun postAndroidMInternetCheck(connectivityManager: ConnectivityManager): Boolean {
        val network = connectivityManager.activeNetwork
        val connection = connectivityManager.getNetworkCapabilities(network)

        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    class NoConnectivityException : IOException() {
        override val message: String?
            get() = "network fail.."
    }
}