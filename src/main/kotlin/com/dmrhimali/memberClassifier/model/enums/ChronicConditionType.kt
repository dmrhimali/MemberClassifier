package com.dmrhimali.memberClassifier.model.enums

enum class ChronicConditionType {
    HYPERTENSION,
    DIABETES,
    ASTHMA,
    ARTHRITIS,
    CANCER,
    OTHER;

    companion object {
        fun isChronicConditionTypeValid(chronicConditionType: String): Boolean {
            return ChronicConditionType.values().any { it.name.equals(chronicConditionType, ignoreCase = true) }
        }
    }
}