package com.dmrhimali.memberClassifier.dto.request

import com.dmrhimali.memberClassifier.model.enums.GenderType
import java.time.LocalDate

data class MemberRequest(
    val name: String,
    val gender: GenderType,
    val address: AddressRequest,
    val healthStatus: HealthStatusRequest,
    val dateOfBirth: LocalDate,
    val contacts: List<ContactRequest>
)
