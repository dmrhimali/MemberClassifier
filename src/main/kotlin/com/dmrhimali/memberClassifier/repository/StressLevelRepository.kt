package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.StressLevelType
import com.dmrhimali.memberClassifier.model.metadata.StressLevel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StressLevelRepository: JpaRepository<StressLevel, Long> {
    fun findByLevel(level: StressLevelType): Optional<StressLevel>
}