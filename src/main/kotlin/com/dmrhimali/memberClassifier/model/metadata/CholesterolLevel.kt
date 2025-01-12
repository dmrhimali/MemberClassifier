package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.CholesterolLevelType
import jakarta.persistence.*

@Entity
@Table(name = "cholesterol_level")
data class CholesterolLevel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, unique = true)
    val level: CholesterolLevelType,

    @Column(name = "description")
    val description: String? = null,  // Optional field for additional info if needed

)