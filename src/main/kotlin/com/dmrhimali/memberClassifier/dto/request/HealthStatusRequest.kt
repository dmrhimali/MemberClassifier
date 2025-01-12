package com.dmrhimali.memberClassifier.dto.request

import com.dmrhimali.memberClassifier.model.enums.*
import kotlin.math.roundToInt

//notice how bmi is removed as it will be calculated from the service
data class HealthStatusRequest(
    val generalHealthRating: GeneralHealthRatingType,
    val mentalHealthStatus: MentalHealthStatusType,
    val height: Double,
    val weight: Double,
    val bloodPressure: String,
    val heartRate: Int,
    val cholesterolLevel: CholesterolLevelType,
    val stressLevel: StressLevelType,
    val chronicConditions: List<ChronicConditionType>,
    val mentalHealthHistory: List<MentalHealthConditionType>,
) {

        fun calculateBMI(): Double {
        return if(height > 0 && weight > 0) {
            val heightInMeters = height / 100 // Convert height to meters
            val bmiValue = weight / (heightInMeters * heightInMeters)
            (bmiValue * 10).roundToInt() / 10.0 // Round to one decimal place
        } else
            0.0
    }
}
