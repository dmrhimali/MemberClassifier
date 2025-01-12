package com.dmrhimali.memberClassifier.dto.response

import com.dmrhimali.memberClassifier.model.classification.ClassificationDefinition

class ClassificationRuleResponse (
    val ruleId: Long,
    val name: String,
    val rule: ClassificationDefinition
)
