package com.example.common.network

sealed class NetworkStatusModel {
    object Unknown : NetworkStatusModel()
    object Connected : NetworkStatusModel()
    object Disconnected : NetworkStatusModel()
}