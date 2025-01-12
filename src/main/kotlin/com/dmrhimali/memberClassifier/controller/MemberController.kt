package com.dmrhimali.memberClassifier.controller

import com.dmrhimali.memberClassifier.dto.request.MemberRequest
import com.dmrhimali.memberClassifier.dto.response.ApiResponse
import com.dmrhimali.memberClassifier.dto.response.ErrorResponse
import com.dmrhimali.memberClassifier.service.MemberService
import com.dmrhimali.memberClassifier.dto.response.MemberResponse
import com.dmrhimali.memberClassifier.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService,
){
    @GetMapping("/healthcheck")
    fun healthCheck(): String {
        return "Application is running!"
    }

    @GetMapping("/all")
    fun getAllMembers(): ResponseEntity<ApiResponse<List<MemberResponse>>> {
        val memberInfoList =  memberService.getAllMembers()
        return ResponseEntity.ok(ApiResponse(success = true, data = memberInfoList))
    }

    @GetMapping("/{memberId}/info")
    fun getMember(@PathVariable memberId: Long): ResponseEntity<ApiResponse<MemberResponse>> {
        return try {
            val memberInfo = memberService.getMember(memberId) // Get member info from service
            ResponseEntity.ok(ApiResponse(success = true, data = memberInfo))
        } catch (e: MemberNotFoundException) {
            // Handle the case where member is not found
            val errorResponse = handleMemberNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: Exception) {
            val errorResponse = handleGenericException(e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse(success = false, error = errorResponse))
        }
    }


    @PostMapping("/save")
    fun saveMember(@RequestBody memberRequest: MemberRequest): ResponseEntity<ApiResponse<MemberResponse>> {
        return try {
            val memberInfo = memberService.saveMember(memberRequest)
            ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse(success = true, data = memberInfo))
        } catch (e: GenderNotFoundException) {
            val errorResponse = handleGenderNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: CholesterolLevelNotFoundException) {
            val errorResponse = handleCholesterolLevelNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: ChronicConditionNotFoundException) {
            val errorResponse = handleChronicConditionNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: GeneralHealthRatingNotFoundException) {
            val errorResponse = handleGeneralHealthRatingNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: MentalHealthConditionNotFoundException) {
            val errorResponse = handleMentalHealthHistoryNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: MentalHealthStatusNotFoundException) {
            val errorResponse = handleMentalHealthStatusNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: StressLevelNotFoundException) {
            val errorResponse = handleStressLevelNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        }
    }

    @ExceptionHandler(Exception::class)
    private fun handleGenericException(e: Exception): ErrorResponse {
        return ErrorResponse(
            errorCode = "INTERNAL_SERVER_ERROR",
            message = e.message ?: "An unexpected error occurred",
        )
    }


    @ExceptionHandler(GenderNotFoundException::class)
    private fun handleGenderNotFoundException(e: GenderNotFoundException): ErrorResponse {
        return ErrorResponse(
            errorCode = "GENDER_NOT_FOUND",
            message = e.message ?: "Gender not found",
        )
    }

    @ExceptionHandler(MemberNotFoundException::class)
    private fun handleMemberNotFoundException(e: MemberNotFoundException): ErrorResponse {
        return ErrorResponse(
            errorCode = "MEMBER_NOT_FOUND",
            message = e.message ?: "Member not found",
        )
    }

    @ExceptionHandler(CholesterolLevelNotFoundException::class)
    private fun handleCholesterolLevelNotFoundException(e: CholesterolLevelNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "CHOLESTEROL_LEVEL_NOT_FOUND",
            message = e.message ?: "Cholesterol level not found",
        )
    }

    @ExceptionHandler(ChronicConditionNotFoundException::class)
    private fun handleChronicConditionNotFoundException(e: ChronicConditionNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "CHRONIC_CONDITION_NOT_FOUND",
            message = e.message ?: "Chronic condition not found",
        )
    }

    @ExceptionHandler(GeneralHealthRatingNotFoundException::class)
    private fun handleGeneralHealthRatingNotFoundException(e: GeneralHealthRatingNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "GENERAL_HEALTH_RATING_NOT_FOUND",
            message = e.message ?: "General health rating not found",
        )
    }

    @ExceptionHandler(MentalHealthConditionNotFoundException::class)
    private fun handleMentalHealthHistoryNotFoundException(e: MentalHealthConditionNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "MENTAL_HEALTH_HISTORY_NOT_FOUND",
            message = e.message ?: "Mental health history not found",
        )
    }

    @ExceptionHandler(MentalHealthStatusNotFoundException::class)
    private fun handleMentalHealthStatusNotFoundException(e: MentalHealthStatusNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "MENTAL_HEALTH_STATUS_NOT_FOUND",
            message = e.message ?: "Mental health status not found",
        )
    }

    @ExceptionHandler(StressLevelNotFoundException::class)
    private fun handleStressLevelNotFoundException(e: StressLevelNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "STRESS_LEVEL_NOT_FOUND",
            message = e.message ?: "Stress level not found",
        )
    }
}