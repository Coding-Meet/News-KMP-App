package com.coding.meet.newsapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val code: String,
    val message: String,
    val status: String,
)
