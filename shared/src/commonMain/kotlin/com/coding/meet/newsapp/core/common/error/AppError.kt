package com.coding.meet.newsapp.core.common.error

sealed class AppError {
    data class Network(val message: String) : AppError()
    data class Api(val code: Int, val message: String) : AppError()
    data object Unknown : AppError()

    fun toMessage(): String = when (this) {
        is Network -> message
        is Api -> "Error $code: $message"
        is Unknown -> "An unknown error occurred"
    }
}
