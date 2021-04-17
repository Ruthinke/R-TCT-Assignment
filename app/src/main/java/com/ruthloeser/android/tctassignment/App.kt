
package com.ruthloeser.android.tctassignment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.ruthloeser.android.tctassignment.networking.NetworkStatusChecker
import com.ruthloeser.android.tctassignment.networking.RemoteApi
import com.ruthloeser.android.tctassignment.networking.buildApiService

class App : Application() {

  companion object {
    private lateinit var instance: App

    private val apiService by lazy { buildApiService() }

    val remoteApi by lazy { RemoteApi(apiService) }

    fun getContext(): Context = instance.applicationContext

    internal fun networkStatusChecker() =
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java))
      }
      else {
        NetworkStatusChecker(instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
      }


  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }


}