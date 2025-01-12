package com.dmrhimali.memberClassifier.util

import com.dmrhimali.memberClassifier.model.classification.ClassificationDefinition
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonParser(
    private val objectMapper: ObjectMapper
) {

    fun serializeToString(classificationDefinition: ClassificationDefinition): String {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(classificationDefinition)
    }

    fun deserializeToClassificationDefinition(json: String): ClassificationDefinition {
        return objectMapper.readValue(json, ClassificationDefinition::class.java)
    }
}