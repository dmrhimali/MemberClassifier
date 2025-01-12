package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.ChronicConditionType
import com.dmrhimali.memberClassifier.model.metadata.ChronicCondition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ChronicConditionRepository: JpaRepository<ChronicCondition, Long> {
    fun findByCondition(condition: ChronicConditionType): Optional<ChronicCondition>
}