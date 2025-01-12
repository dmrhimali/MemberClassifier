package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.GenderType

data class GenderResponse(
    val type: GenderType,
    val description: String? = null,
)