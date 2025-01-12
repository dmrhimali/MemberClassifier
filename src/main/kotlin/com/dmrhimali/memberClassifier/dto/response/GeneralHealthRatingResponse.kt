package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.enums.GeneralHealthRatingType

data class GeneralHealthRatingResponse(
    val type: GeneralHealthRatingType,
    val description: String? = null,
)