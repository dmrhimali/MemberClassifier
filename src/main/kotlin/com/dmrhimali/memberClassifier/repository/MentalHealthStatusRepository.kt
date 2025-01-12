package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.MentalHealthStatusType
import com.dmrhimali.memberClassifier.model.metadata.MentalHealthStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MentalHealthStatusRepository: JpaRepository<MentalHealthStatus, Long> {
    fun findByType(type: MentalHealthStatusType): Optional<MentalHealthStatus>
}