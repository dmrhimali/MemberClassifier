package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.CholesterolLevelType
import com.dmrhimali.memberClassifier.model.metadata.CholesterolLevel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CholesterolLevelRepository: JpaRepository<CholesterolLevel, Long> {
    fun findByLevel(level: CholesterolLevelType): Optional<CholesterolLevel>
}