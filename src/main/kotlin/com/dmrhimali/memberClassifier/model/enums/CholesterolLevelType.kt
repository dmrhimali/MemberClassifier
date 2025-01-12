package com.dmrhimali.memberClassifier.model.enums

import com.dmrhimali.memberClassifier.model.metadata.CholesterolLevel

enum class CholesterolLevelType {
    NORMAL,
    BORDERLINE,
    HIGH,
    VERY_HIGH;

    companion object {
        fun isCholesterolLevelValid(cholesterolLevel: String): Boolean {
            return values().any { it.name.equals(cholesterolLevel, ignoreCase = true) }
        }
    }
}