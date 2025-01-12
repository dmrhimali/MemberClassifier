package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.MentalHealthStatusType

data class MentalHealthStatusResponse(
    val type: MentalHealthStatusType,
    val description: String? = null,
)