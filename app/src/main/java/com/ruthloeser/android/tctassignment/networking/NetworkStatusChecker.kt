package com.ruthloeser.android.tctassignment.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class NetworkStatusChecker(private val connectivityManager: ConnectivityManager?) {

  inline fun performIfConnectedToInternet(action: () -> Unit) {
    if (hasInternetConnection()) {
      action()
    }
  }

  fun hasInternetConnection(): Boolean {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

      val network = connectivityManager?.activeNetwork ?: return false
      val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

      return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
              || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
              || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)

    }
    else {
      connectivityManager?.activeNetworkInfo?.run {
        return type == ConnectivityManager.TYPE_WIFI ||
                type == ConnectivityManager.TYPE_MOBILE ||
                type == ConnectivityManager.TYPE_ETHERNET
      }
    }

    return false
  }
}
