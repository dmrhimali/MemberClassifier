package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.MentalHealthStatusType
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "mental_health_status")
data class MentalHealthStatus (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val type: MentalHealthStatusType,

    @Column(name = "description")
    val description: String? = null
)