package com.dmrhimali.memberClassifier.service

import com.dmrhimali.memberClassifier.dto.response.*
import com.dmrhimali.memberClassifier.repository.*
import org.springframework.stereotype.Service


@Service
class HealthService(
    private val cholesterolLevelRepository: CholesterolLevelRepository,
    private val chronicConditionRepository: ChronicConditionRepository,
    private val genderRepository: GenderRepository,
    private val generalHealthRatingRepository: GeneralHealthRatingRepository,
    private val mentalHealthConditionRepository: MentalHealthConditionRepository,
    private val mentalHealthStatusRepository: MentalHealthStatusRepository,
    private val stressLevelRepository: StressLevelRepository
) {

    fun getCholesterolLevels(): List<CholesterolLevelResponse> {
        return cholesterolLevelRepository.findAll().map {
            CholesterolLevelResponse(it.level, it.description)
        }
    }

    fun getChronicConditions(): List<ChronicConditionResponse> {
        return chronicConditionRepository.findAll().map {
            ChronicConditionResponse(it.condition, it.description)
        }
    }

    fun getGenders() : List<GenderResponse> {
        return genderRepository.findAll().map {
            GenderResponse(it.type, it.description)
        }
    }

    fun getGeneralHealthRatings() : List<GeneralHealthRatingResponse> {
        return generalHealthRatingRepository.findAll().map {
            GeneralHealthRatingResponse(it.type, it.description)
        }
    }

    fun getMentalHealthConditions() : List<MentalHealthConditionResponse> {
        return mentalHealthConditionRepository.findAll().map {
            MentalHealthConditionResponse(it.condition, it.description)
        }
    }

    fun getMentalHealthStatuses() : List<MentalHealthStatusResponse> {
        return mentalHealthStatusRepository.findAll().map {
            MentalHealthStatusResponse(it.type, it.description)
        }
    }

    fun getStressLevels() : List<StressLevelResponse> {
        return stressLevelRepository.findAll().map {
            StressLevelResponse(it.level, it.description)
        }
    }

}