package com.example.common.network

import com.example.common.exception.ConnectionError
import com.example.common.exception.NotFoundError
import com.example.common.exception.TimeoutError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {

            when (throwable) {
                is TimeoutException, is SocketTimeoutException -> {
                    throw TimeoutError()
                }

                is IOException -> {
                    throw ConnectionError()
                }

                is HttpException -> {
                    // TODO : check for error codes
                    throw NotFoundError()
                }

                else -> {
                    throw UnknownError()
                }
            }
        }
    }
}
