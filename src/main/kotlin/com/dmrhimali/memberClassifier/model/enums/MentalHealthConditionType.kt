package com.dmrhimali.memberClassifier.model.enums

enum class MentalHealthConditionType {
    DEPRESSION,
    ANXIETY,
    BIPOLAR_DISORDER,
    SCHIZOPHRENIA,
    OTHER;

    companion object {
        fun isMentalHealthConditionTypeValid(mentalHealthConditionType: String): Boolean {
            return MentalHealthConditionType.values().any { it.name.equals(mentalHealthConditionType, ignoreCase = true) }
        }
    }
}