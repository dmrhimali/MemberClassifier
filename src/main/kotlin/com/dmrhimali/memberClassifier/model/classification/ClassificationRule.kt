package com.dmrhimali.memberClassifier.model.classification

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "classification_rules")
data class ClassificationRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "rule_id", nullable = false)
    val id: Long = 0,

    @Column( name = "rule_name", nullable = false)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "classification_definition", nullable = false, columnDefinition = "jsonb")
    var rule: ClassificationDefinition

)
