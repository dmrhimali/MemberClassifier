package com.dmrhimali.memberClassifier.dto.request

import com.dmrhimali.memberClassifier.model.Address

data class AddressRequest(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
)
