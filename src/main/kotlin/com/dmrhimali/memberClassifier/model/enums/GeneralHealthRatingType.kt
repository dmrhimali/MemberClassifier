package com.dmrhimali.memberClassifier.model.enums

enum class GeneralHealthRatingType {
    EXCELLENT,
    GOOD,
    FAIR,
    POOR;

    companion object {
        fun isGeneralHealthRatingTypeValid(generalHealthRatingType: String): Boolean {
            return GeneralHealthRatingType.values().any { it.name.equals(generalHealthRatingType, ignoreCase = true) }
        }
    }
}