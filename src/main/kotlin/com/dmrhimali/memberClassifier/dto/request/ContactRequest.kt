package com.dmrhimali.memberClassifier.dto.request

import com.dmrhimali.memberClassifier.model.enums.ContactType

data class ContactRequest(
    val type: ContactType,
    val value: String
)
