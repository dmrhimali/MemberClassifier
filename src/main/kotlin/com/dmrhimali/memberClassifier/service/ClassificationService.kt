package com.dmrhimali.memberClassifier.service

import com.dmrhimali.memberClassifier.dto.request.ClassificationRuleRequest
import com.dmrhimali.memberClassifier.dto.response.ClassificationRuleResponse
import com.dmrhimali.memberClassifier.dto.response.MemberResponse
import com.dmrhimali.memberClassifier.exceptions.ClassificationRuleIdNotSpecifiedException
import com.dmrhimali.memberClassifier.exceptions.ClassificationRuleNotFoundException
import com.dmrhimali.memberClassifier.model.Member
import com.dmrhimali.memberClassifier.model.classification.ClassificationDefinition
import com.dmrhimali.memberClassifier.model.classification.ClassificationRule
import com.dmrhimali.memberClassifier.repository.ClassificationRulesRepository
import com.dmrhimali.memberClassifier.util.JsonParser
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import org.springframework.stereotype.Service

@Service
class ClassificationService(
    private val classificationRulesRepository: ClassificationRulesRepository,
    private val jsonParser: JsonParser
) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun saveClassificationRule(classificationRuleRequest: ClassificationRuleRequest): ClassificationRuleResponse {
        val savedRule = classificationRulesRepository.save(
            ClassificationRule(
                name = classificationRuleRequest.name,
                rule =  classificationRuleRequest.rule
            )
        )

        return ClassificationRuleResponse(
            ruleId = savedRule.id,
            name = savedRule.name,
            rule = savedRule.rule
        )
    }

    fun getAllClassificationRules(): List<ClassificationRuleResponse> {
        val classificationRules = classificationRulesRepository.findAll()
        return classificationRules.map {
            ClassificationRuleResponse(
                ruleId = it.id,
                name = it.name,
                rule = it.rule
            )
        }
    }


    fun updateClassificationRule(classificationRuleRequest: ClassificationRuleRequest): ClassificationRuleResponse{
        if(classificationRuleRequest.ruleId==null) {
            throw ClassificationRuleIdNotSpecifiedException("classification id not specified")
        }

        val existingClassificationRule = classificationRulesRepository.findById(classificationRuleRequest.ruleId)
            .orElseThrow { ClassificationRuleNotFoundException("Classification rule with id ${classificationRuleRequest.ruleId} not found.")}

        existingClassificationRule.name = classificationRuleRequest.name
        existingClassificationRule.rule = classificationRuleRequest.rule
        val savedRule = classificationRulesRepository.save(existingClassificationRule)

        return ClassificationRuleResponse(
            ruleId = savedRule.id,
            name = savedRule.name,
            rule = savedRule.rule
        )
    }

    fun getMembersByClassificationRuleId(ruleId: Long): List<MemberResponse>? {
        //get classification definition
        val classificationRule = classificationRulesRepository.findById(ruleId)
            .orElseThrow { ClassificationRuleNotFoundException("Classification rule with id $ruleId not found.")}
        val classificationDefinition = classificationRule.rule
        val sql = convertToSqlQuery(classificationDefinition)
        //val memberList =  memberRepository.findMembersByCustomQuery(sql)
        val query: Query = entityManager.createNativeQuery(sql, Member::class.java)
        val memberList = query.resultList as List<Member>

        return memberList.map {
            MemberResponse.fromMember(it)
        }

    }

    private fun convertToSqlQuery(classificationDefinition: ClassificationDefinition): String {
        val sqlBuilder = StringBuilder()
        sqlBuilder.append("SELECT DISTINCT m.* FROM member m " +
                "LEFT JOIN health_status hs ON hs.member_id = m.id " +
                "LEFT JOIN gender g ON g.id = m.gender_id " +
                "LEFT JOIN address a ON a.member_id = m.id " +
                "LEFT JOIN general_health_rating ghr ON ghr.id = hs.general_health_rating_id " +
                "LEFT JOIN mental_health_status mhs ON mhs.id = hs.mental_health_status_id " +
                "LEFT JOIN cholesterol_level cl ON cl.id = hs.cholesterol_level_id " +
                "LEFT JOIN stress_level sl ON sl.id = hs.stress_level_id " +
                "LEFT JOIN health_status_chronic_condition hscc ON hscc.health_status_id = hs.id " +
                "LEFT JOIN chronic_condition cc ON cc.id = hscc.chronic_condition_id " +
                "LEFT JOIN health_status_mental_health_condition hsmhc ON hsmhc.health_status_id = hs.id " +
                "LEFT JOIN mental_health_condition mhc ON mhc.id = hsmhc.mental_health_condition_id " )


        val whereClause = classificationDefinition.getWhereClause()

        val sql : String = if (!whereClause.isNullOrEmpty()) {
            sqlBuilder.append( " WHERE $whereClause").toString()
        } else {
            sqlBuilder.toString()
        }

        return sql
    }



}