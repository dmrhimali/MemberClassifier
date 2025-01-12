package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.GenderType
import com.dmrhimali.memberClassifier.model.metadata.Gender
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface GenderRepository: JpaRepository<Gender, Long> {
    fun findByType(type: GenderType): Optional<Gender>
}