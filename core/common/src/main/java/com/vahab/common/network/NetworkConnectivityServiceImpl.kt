package com.vahab.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

/*
    I did not used it is a nice class for handling the network connectivity and
    it a good example of "callbackFlow" useCase :D
 */
class NetworkConnectivityServiceImpl @Inject constructor(
    context: Context,
    dispatcher: CoroutineDispatcher
) : NetworkConnectivityService {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val networkStatus: Flow<NetworkStatusModel> =
        callbackFlow {
            val connectivityCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(NetworkStatusModel.Connected)
                }

                override fun onUnavailable() {
                    trySend(NetworkStatusModel.Disconnected)
                }

                override fun onLost(network: Network) {
                    trySend(NetworkStatusModel.Disconnected)
                }

            }

            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()

            connectivityManager.registerNetworkCallback(request, connectivityCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(connectivityCallback)
            }
        }
            .distinctUntilChanged()
            .flowOn(dispatcher)

}
