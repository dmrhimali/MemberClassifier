package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.CholesterolLevelType

data class CholesterolLevelResponse(
    val level: CholesterolLevelType,
    val description: String? = null,
)