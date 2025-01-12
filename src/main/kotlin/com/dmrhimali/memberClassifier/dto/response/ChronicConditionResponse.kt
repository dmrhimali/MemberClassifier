package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.ChronicConditionType

data class ChronicConditionResponse(
    val condition: ChronicConditionType,
    val description: String? = null,
)