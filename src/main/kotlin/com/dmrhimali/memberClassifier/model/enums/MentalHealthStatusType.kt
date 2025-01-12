package com.dmrhimali.memberClassifier.model.enums

enum class MentalHealthStatusType {
    COPING_WELL,
    UNDER_TREATMENT,
    EXPERIENCING_SYMPTOMS;

    companion object {
        fun isMentalHealthStatusTypeValid(mentalHealthStatusType: String): Boolean {
            return MentalHealthStatusType.values().any { it.name.equals(mentalHealthStatusType, ignoreCase = true) }
        }
    }
}