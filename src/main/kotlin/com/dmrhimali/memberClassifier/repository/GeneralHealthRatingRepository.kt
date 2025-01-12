package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.enums.GeneralHealthRatingType
import com.dmrhimali.memberClassifier.model.metadata.GeneralHealthRating
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface GeneralHealthRatingRepository: JpaRepository<GeneralHealthRating, Long> {
    fun findByType(type: GeneralHealthRatingType): Optional<GeneralHealthRating>
}