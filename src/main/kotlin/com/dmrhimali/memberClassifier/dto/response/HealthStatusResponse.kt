package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.HealthStatus
import com.dmrhimali.memberClassifier.model.enums.CholesterolLevelType
import com.dmrhimali.memberClassifier.model.enums.ChronicConditionType
import com.dmrhimali.memberClassifier.model.enums.MentalHealthConditionType
import com.dmrhimali.memberClassifier.model.enums.StressLevelType
import com.dmrhimali.memberClassifier.model.metadata.*

data class HealthStatusResponse (
    val generalHealthRating: GeneralHealthRating,
    val mentalHealthStatus: MentalHealthStatus,
    val height: Double,
    val weight: Double,
    val bmi: Double,
    val bloodPressure: String,
    val cholesterolLevel: CholesterolLevelType,
    val stressLevels: StressLevelType,
    val chronicConditions: List<ChronicConditionType>,
    val mentalHealthHistory: List<MentalHealthConditionType>
) {
    companion object {
        fun fromHealthStatus(healthStatus: HealthStatus?) : HealthStatusResponse? {
            if (healthStatus != null) {
                return HealthStatusResponse(
                    generalHealthRating = healthStatus.generalHealthRating,
                    mentalHealthStatus = healthStatus.mentalHealthStatus,
                    height = healthStatus.height,
                    weight = healthStatus.weight,
                    bmi = healthStatus.calculateBMI(),
                    bloodPressure = healthStatus.bloodPressure,
                    cholesterolLevel = healthStatus.cholesterolLevel.level,
                    stressLevels = healthStatus.stressLevels.level,
                    chronicConditions = healthStatus.chronicConditions.map { it.condition },
                    mentalHealthHistory = healthStatus.mentalHealthConditions.map { it.condition }
                )
            }
            return null;
        }
    }

}
