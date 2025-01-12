package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.GeneralHealthRatingType
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "general_health_rating")
data class GeneralHealthRating (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "rating", nullable = false)
    val type: GeneralHealthRatingType,

    @Column(name = "description")
    val description: String? = null
)