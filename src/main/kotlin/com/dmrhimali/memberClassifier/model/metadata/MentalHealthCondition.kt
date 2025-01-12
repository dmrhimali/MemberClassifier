package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.MentalHealthConditionType
import jakarta.persistence.*

@Entity
@Table(name = "mental_health_condition")
data class MentalHealthCondition (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, unique = true)
    val condition: MentalHealthConditionType,

    @Column(name = "description")
    val description: String? = null,  // Optional field for additional info if needed
)