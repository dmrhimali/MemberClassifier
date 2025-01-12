package com.dmrhimali.memberClassifier.controller

import com.dmrhimali.memberClassifier.dto.request.ClassificationRuleRequest
import com.dmrhimali.memberClassifier.dto.response.ApiResponse
import com.dmrhimali.memberClassifier.dto.response.ClassificationRuleResponse
import com.dmrhimali.memberClassifier.dto.response.ErrorResponse
import com.dmrhimali.memberClassifier.dto.response.MemberResponse
import com.dmrhimali.memberClassifier.exceptions.ClassificationRuleIdNotSpecifiedException
import com.dmrhimali.memberClassifier.exceptions.ClassificationRuleNotFoundException
import com.dmrhimali.memberClassifier.exceptions.StressLevelNotFoundException
import com.dmrhimali.memberClassifier.service.ClassificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classification_rules")
class MemberClassificationController(
    private val classificationService: ClassificationService
) {

    @PostMapping("/save")
    fun saveClassificationRule(@RequestBody classificationRuleRequest: ClassificationRuleRequest): ResponseEntity<ApiResponse<ClassificationRuleResponse>> {
        println("classificationRuleRequest save= $classificationRuleRequest")
        val classificationRuleResponse = classificationService.saveClassificationRule(classificationRuleRequest = classificationRuleRequest)
        return ResponseEntity.ok(ApiResponse(success = true, data = classificationRuleResponse))
    }

    @PostMapping("/update")
    fun updateClassificationRule(@RequestBody classificationRuleRequest: ClassificationRuleRequest): ResponseEntity<ApiResponse<ClassificationRuleResponse>> {
        return try{
            val classificationRuleResponse = classificationService.updateClassificationRule(classificationRuleRequest = classificationRuleRequest)
            return ResponseEntity.ok(ApiResponse(success = true, data = classificationRuleResponse))
        } catch (e: ClassificationRuleIdNotSpecifiedException) {
            val errorResponse = handleClassificationRuleIdNotSpecifiedException(e)
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse(success = false, error = errorResponse))
        } catch (e: ClassificationRuleNotFoundException) {
            val errorResponse = handleClassificationRuleNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        }

    }


    @GetMapping("/all")
    fun getAllClassificationRules() : ResponseEntity<ApiResponse<List<ClassificationRuleResponse>>> {
        val classificationRuleResponses = classificationService.getAllClassificationRules()
        return ResponseEntity.ok(ApiResponse(success = true, data = classificationRuleResponses))
    }

    @GetMapping("/{ruleId}/members")
    fun getMembersByClassificationRule(@PathVariable ruleId: Long): ResponseEntity<ApiResponse<List<MemberResponse>>> {
        return try {
            var memberResponsesList = classificationService.getMembersByClassificationRuleId(ruleId)
            ResponseEntity.ok(ApiResponse(success = true, data = memberResponsesList))
        } catch (e: ClassificationRuleNotFoundException) {
            val errorResponse = handleClassificationRuleNotFoundException(e)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(success = false, error = errorResponse))
        }
    }

    @ExceptionHandler(ClassificationRuleNotFoundException::class)
    private fun handleClassificationRuleNotFoundException(e: ClassificationRuleNotFoundException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "CLASSIFICATION_RULE_NOT_FOUND",
            message = e.message ?: "Classification rule not found",
        )
    }
    @ExceptionHandler(ClassificationRuleIdNotSpecifiedException::class)
    private fun handleClassificationRuleIdNotSpecifiedException(e: ClassificationRuleIdNotSpecifiedException): ErrorResponse? {
        return ErrorResponse(
            errorCode = "CLASSIFICATION_RULE_ID_NOT_SPECIFIED",
            message = e.message ?: "Classification rule id not specified",
        )
    }
}