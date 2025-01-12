package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.StressLevelType
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "stress_level")
data class StressLevel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    val level: StressLevelType,

    @Column(name = "description")
    val description: String? = null
)