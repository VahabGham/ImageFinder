package com.example.imagefinder.common.network

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityService {
    val networkStatus: Flow<NetworkStatusModel>
}
