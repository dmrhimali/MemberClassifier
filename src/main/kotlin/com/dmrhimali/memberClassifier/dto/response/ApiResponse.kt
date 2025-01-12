package com.dmrhimali.memberClassifier.dto.response

data class ApiResponse<T> (
    val success: Boolean,
    val data: T? = null,
    val error: ErrorResponse? = null
)