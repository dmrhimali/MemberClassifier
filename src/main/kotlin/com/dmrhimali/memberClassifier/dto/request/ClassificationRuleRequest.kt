package com.dmrhimali.memberClassifier.dto.request

import com.dmrhimali.memberClassifier.model.classification.ClassificationDefinition

class ClassificationRuleRequest (
    val ruleId: Long? = 0,
    val name: String,
    val rule: ClassificationDefinition
){
    constructor() : this(0, "", ClassificationDefinition())
}
