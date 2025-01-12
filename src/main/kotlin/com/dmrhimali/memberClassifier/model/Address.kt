package com.dmrhimali.memberClassifier.model

import jakarta.persistence.*

@Entity
@Table(name= "address")
data class Address  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "street", nullable = false)
    val street: String,

    @Column(name = "city", nullable = false)
    val city: String,

    @Column(name = "state", nullable = false)
    val state: String,

    @Column(name = "zipcode", nullable = false)
    val zipCode: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,
)