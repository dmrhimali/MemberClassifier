package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.ChronicConditionType
import jakarta.persistence.*

@Entity
@Table(name = "chronic_condition")
data class ChronicCondition (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, unique = true)
    val condition: ChronicConditionType,

    @Column(name = "description")
    val description: String? = null,  // Optional field for additional info if needed
)