package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.HealthStatus
import org.springframework.data.jpa.repository.JpaRepository

interface HealthStatusRepository: JpaRepository<HealthStatus, Long>