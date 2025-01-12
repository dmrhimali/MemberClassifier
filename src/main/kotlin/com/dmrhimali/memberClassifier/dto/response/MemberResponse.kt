package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.HealthStatus
import com.dmrhimali.memberClassifier.model.Member
import com.dmrhimali.memberClassifier.model.metadata.Gender
import java.time.LocalDate

//notice that  contacts, address, or cascading relationships are not exposed to client

data class MemberResponse(
    val memberId: Long,               // Member ID
    val name: String,           // Member's Name
    val gender: Gender,         // Member's Gender
    val age: Int,               // Calculated Age
    val dateOfBirth: LocalDate, // Date of Birth
    val healthStatus: HealthStatusResponse?,

    ){

    companion object {
        fun fromMember(member: Member) : MemberResponse {
            return MemberResponse(
                memberId = member.id,
                name = member.name,
                gender = member.gender,
                age = member.age!!, //the @PostLoad calculateAgeOnLoad() will calculate age when member is loaded from database
                dateOfBirth = member.dateOfBirth,
                healthStatus = HealthStatusResponse.fromHealthStatus(member.healthStatus)
            )
        }
    }
}