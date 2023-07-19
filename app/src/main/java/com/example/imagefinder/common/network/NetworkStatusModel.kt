package com.example.imagefinder.common.network

sealed class NetworkStatusModel {
    object Unknown : NetworkStatusModel()
    object Connected : NetworkStatusModel()
    object Disconnected : NetworkStatusModel()
}