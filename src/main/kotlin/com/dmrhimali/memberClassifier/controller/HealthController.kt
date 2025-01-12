package com.dmrhimali.memberClassifier.controller

import com.dmrhimali.memberClassifier.dto.response.*
import com.dmrhimali.memberClassifier.service.HealthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController(
    private val healthService: HealthService
) {

    @GetMapping("/cholesterol_levels")
    fun getCholesterolLevels() : ResponseEntity<ApiResponse<List<CholesterolLevelResponse>>> {
        val cholesterolLevelsList = healthService.getCholesterolLevels()
        return ResponseEntity.ok(ApiResponse(success = true, data = cholesterolLevelsList))
    }

    @GetMapping("/chronic_conditions")
    fun getChronicConditions() : ResponseEntity<ApiResponse<List<ChronicConditionResponse>>> {
        val chronicConditions = healthService.getChronicConditions()
        return ResponseEntity.ok(ApiResponse(success = true, data = chronicConditions))
    }

    @GetMapping("/genders")
    fun getGenders() : ResponseEntity<ApiResponse<List<GenderResponse>>> {
        val genders = healthService.getGenders()
        return ResponseEntity.ok(ApiResponse(success = true, data = genders))
    }

    @GetMapping("/general_health_ratings")
    fun getGeneralHealthRatings() : ResponseEntity<ApiResponse<List<GeneralHealthRatingResponse>>> {
        val generalHealthRatings = healthService.getGeneralHealthRatings()
        return ResponseEntity.ok(ApiResponse(success = true, data = generalHealthRatings))
    }

    @GetMapping("/mental_health_conditions")
    fun getMentalHealthConditions() : ResponseEntity<ApiResponse<List<MentalHealthConditionResponse>>> {
        val mentalHealthConditions = healthService.getMentalHealthConditions()
        return ResponseEntity.ok(ApiResponse(success = true, data = mentalHealthConditions))
    }

    @GetMapping("/mental_health_statuses")
    fun getMentalHealthStatuses() : ResponseEntity<ApiResponse<List<MentalHealthStatusResponse>>> {
        val mentalHealthStatuses = healthService.getMentalHealthStatuses()
        return ResponseEntity.ok(ApiResponse(success = true, data = mentalHealthStatuses))
    }

    @GetMapping("/stress_levels")
    fun getStressLevels() : ResponseEntity<ApiResponse<List<StressLevelResponse>>> {
        val stressLevels = healthService.getStressLevels()
        return ResponseEntity.ok(ApiResponse(success = true, data = stressLevels))
    }


}