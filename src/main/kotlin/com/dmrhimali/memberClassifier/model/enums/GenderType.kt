package com.dmrhimali.memberClassifier.model.enums

enum class GenderType {
    MALE,
    FEMALE,
    OTHER;

    companion object {
        fun isGenderTypeValid(genderType: String): Boolean {
            return GenderType.values().any { it.name.equals(genderType, ignoreCase = true) }
        }
    }
}
