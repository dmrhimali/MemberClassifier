package com.dmrhimali.memberClassifier.model

import com.dmrhimali.memberClassifier.model.enums.ContactType
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "contact")
data class Contact (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    val type: ContactType,

    @Column(name = "value", nullable = false)
    val value: String,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member
)