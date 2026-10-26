package com.coding.meet.newsapp.core.network.safecall

import com.coding.meet.newsapp.core.common.error.AppError
import com.coding.meet.newsapp.core.common.result.AppResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(block: suspend () -> T): AppResult<T> = try {
    AppResult.Success(block())
} catch (e: ClientRequestException) {
    AppResult.Error(AppError.Api(e.response.status.value, e.message.ifBlank { "Client error" }))
} catch (e: ServerResponseException) {
    AppResult.Error(AppError.Api(e.response.status.value, e.message.ifBlank { "Server error" }))
} catch (e: Exception) {
    AppResult.Error(AppError.Network(e.message ?: "Network error"))
}
