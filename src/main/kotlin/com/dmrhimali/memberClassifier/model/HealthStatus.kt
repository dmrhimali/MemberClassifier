package com.dmrhimali.memberClassifier.model

import com.dmrhimali.memberClassifier.model.metadata.*
import jakarta.persistence.*
import kotlin.math.roundToInt

@Entity
@Table(name = "health_status")
data class HealthStatus (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "general_health_rating_id", nullable = false)
    val generalHealthRating: GeneralHealthRating,

    @ManyToOne
    @JoinColumn(name = "mental_health_status_id", nullable = false)
    val mentalHealthStatus: MentalHealthStatus,

    // Physical Health
    val height: Double, // Height in cm

    val weight: Double, // Weight in kg

    val bmi: Double, // BMI , persisted in database , thus not @Transient

    @Column(name = "blood_pressure")
    val bloodPressure: String, // Blood pressure level, e.g., "120/80"

    @Column(name = "heart_rate")
    val heartRate: Int, // Heart rate in BPM

    @ManyToOne
    @JoinColumn(name = "cholesterol_level_id", nullable = false)
    val cholesterolLevel: CholesterolLevel,

    @ManyToOne
    @JoinColumn(name = "stress_level_id", nullable = false)
    val stressLevels: StressLevel, // Stress levels

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "health_status_chronic_condition",  // The join table name
        joinColumns = [JoinColumn(name = "health_status_id")],  // Foreign key to HealthStatus
        inverseJoinColumns = [JoinColumn(name = "chronic_condition_id")]  // Foreign key to ChronicConditionEntity
    )
    val chronicConditions: Set<ChronicCondition> = emptySet(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "health_status_mental_health_condition",  // The join table name
        joinColumns = [JoinColumn(name = "health_status_id")],  // Foreign key to HealthStatus
        inverseJoinColumns = [JoinColumn(name = "mental_health_condition_id")]  // Foreign key to MentalHealthHistory
    )
    val mentalHealthConditions: Set<MentalHealthCondition> = emptySet(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    ) {
    //in HealthStatusRepository getHealthStatus() method, call this and update member object before returning
    //in HealthStatusRepository saveHealthStatus(healthStatus: HealthStatus) method, call this and update member object before saving
    fun calculateBMI(): Double {
        return if(height > 0 && weight > 0) {
            val heightInMeters = height / 100 // Convert height to meters
            val bmiValue = weight / (heightInMeters * heightInMeters)
            (bmiValue * 10).roundToInt() / 10.0 // Round to one decimal place
        } else
            0.0
    }
}
