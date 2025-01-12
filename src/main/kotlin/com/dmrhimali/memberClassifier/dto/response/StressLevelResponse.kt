package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.StressLevelType

data class StressLevelResponse(
    val level: StressLevelType,
    val description: String? = null,
)