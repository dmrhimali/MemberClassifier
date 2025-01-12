package com.dmrhimali.memberClassifier.dto.response

import java.time.LocalDateTime

data class ErrorResponse(
    val errorCode : String,
    val message: String,
    val timestamp: String? = LocalDateTime.now().toString(),
    val details: String? = null
)
