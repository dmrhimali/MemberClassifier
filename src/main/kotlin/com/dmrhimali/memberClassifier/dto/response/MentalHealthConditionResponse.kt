package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.MentalHealthConditionType

data class MentalHealthConditionResponse(
    val condition: MentalHealthConditionType,
    val description: String? = null,
)