package com.dmrhimali.memberClassifier.model.metadata

import com.dmrhimali.memberClassifier.model.enums.GenderType
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "gender")
data class Gender (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    val id: Long = 0, // The id will be auto-generated, you don't need to provide it


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: GenderType,

    @Column(name = "description")
    val description: String? = null
)