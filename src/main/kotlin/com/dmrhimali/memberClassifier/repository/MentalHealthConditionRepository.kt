package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.MentalHealthConditionType
import com.dmrhimali.memberClassifier.model.metadata.MentalHealthCondition
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MentalHealthConditionRepository: JpaRepository<MentalHealthCondition, Long> {
    fun findByCondition(condition: MentalHealthConditionType): Optional<MentalHealthCondition>
}