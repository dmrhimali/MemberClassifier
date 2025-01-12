package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.classification.ClassificationRule
import org.springframework.data.jpa.repository.JpaRepository

interface ClassificationRulesRepository: JpaRepository<ClassificationRule, Long>