package com.dmrhimali.memberClassifier.model.enums

enum class StressLevelType {
    LOW,
    MODERATE,
    HIGH;


    companion object {
        fun isStressLevelTypeValid(stressLevelType: String): Boolean {
            return StressLevelType.values().any { it.name.equals(stressLevelType, ignoreCase = true) }
        }
    }
}