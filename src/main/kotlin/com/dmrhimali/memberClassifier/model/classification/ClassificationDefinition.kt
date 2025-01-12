package com.dmrhimali.memberClassifier.model.classification

import com.dmrhimali.memberClassifier.exceptions.*
import com.dmrhimali.memberClassifier.model.enums.*
import com.fasterxml.jackson.annotation.*
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class  ClassificationDefinition(
    val member: MemberFilter? = null,
    val healthStatus: HealthStatusFilter? = null,

    val and: MutableList<ClassificationDefinition>? = null,
    val or: MutableList<ClassificationDefinition>? = null,
    val not: ClassificationDefinition? = null
){
    @JsonIgnore
    fun getWhereClause(): String? {
        val sqlBuilder = StringBuilder()

        //member clause
        member?.getSqlWhereClause()?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it)
        }

        //healthstatus clause
        healthStatus?.getSqlWhereClause()?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it)
        }

        //and clause
        var andSqlBuilder = StringBuilder("")
        and?.let {
            it.forEach { classificationDefinition ->
                if (andSqlBuilder.isNotEmpty()) { andSqlBuilder.append(" AND ")}
                andSqlBuilder.append(classificationDefinition.getWhereClause())
            }

            andSqlBuilder = StringBuilder("($andSqlBuilder)")
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(andSqlBuilder)
        }


        //or clause
        var orSqlBuilder = StringBuilder("")
        or?.let { it ->
            it.forEach { classificationDefinition ->
                if (orSqlBuilder.isNotEmpty()) { orSqlBuilder.append(" OR ")}
                orSqlBuilder.append(classificationDefinition.getWhereClause())
            }
            orSqlBuilder = StringBuilder("($orSqlBuilder)")

            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(orSqlBuilder)
        }

        //not clause
        not?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(" NOT ${it.getWhereClause()}")
        }

        return sqlBuilder.toString()

    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MemberFilter(
    val memberId: NumberFilter? = null,
    val gender: StringFilter? = null,
    val state: StringFilter? = null,
    val dateOfBirth: DateFilter? = null,
    val age: NumberFilter? = null,
) {
    @JsonIgnore
    fun getSqlWhereClause(): String? {
        val sqlBuilder = StringBuilder()

        memberId?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("m.id"))
        }

        gender?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("g.type"))
        }

        state?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("a.state"))
        }

        dateOfBirth?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("m.date_of_birth"))
        }

        age?.let { it ->
            val dateFilter = DateFilter(
                equals = it.equals?.let { it1 -> LocalDate.now().minusYears(it1) },
                gt = it.lt?.let { it1 -> LocalDate.now().minusYears(it1) },
                gte = it.lte?.let { it1 -> LocalDate.now().minusYears(it1) },
                lt = it.gt?.let { it1 -> LocalDate.now().minusYears(it1) },
                lte = it.gte?.let { it1 -> LocalDate.now().minusYears(it1) },
                between = it.between?.let { numberRange ->
                    DateRange(
                        start = numberRange.end.let { it1 -> LocalDate.now().minusYears(it1) },
                        end = numberRange.start.let { it1 -> LocalDate.now().minusYears(it1) },
                    )
                }

            )

            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(dateFilter.getSqlWhereClause("m.date_of_birth"))
        }

        return sqlBuilder.toString()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class HealthStatusFilter(
    val generalHealthRating: StringFilter? = null,
    val mentalHealthStatus: StringFilter? = null,
    val bmi: NumberFilter? = null,
    val bloodPressure: NumberFilter? = null,
    val cholesterolLevel: StringFilter? = null,
    val stressLevel: StringFilter? = null,
    val chronicConditions: StringFilter? = null,
    val mentalHealthConditions: StringFilter? = null
){
    @JsonIgnore
    fun getSqlWhereClause(): String? {

        val sqlBuilder = StringBuilder()

        generalHealthRating?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("ghr.rating"))
        }

        mentalHealthStatus?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("mhs.status"))
        }

        bmi?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("hs.bmi"))
        }

        bloodPressure?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("hs.blood_pressure"))
        }

        cholesterolLevel?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("cl.level"))
        }

        stressLevel?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("sl.level"))
        }

        chronicConditions?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("cc.condition"))
        }

        mentalHealthConditions?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append(it.getSqlWhereClause("mhc.condition"))
        }

        return sqlBuilder.toString()
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class StringFilter(
     val equals: String? = null,
     val contains: String? = null,
    @JsonProperty("isIn") val isIn: List<String>? = null
) {

    @JsonIgnore
    fun getSqlWhereClause(field: String): String? {
        val sqlBuilder = StringBuilder()

        equals?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field = '$it'")
        }

        contains?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field LIKE '%$it%'")
        }

        isIn?.let { list ->
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            val stringList  = list.map { "'${it}'" }
            val sqlStringList = stringList.joinToString(prefix = "(", postfix = ")") //convert list to surround by parenthesis instead
            sqlBuilder.append("$field IN $sqlStringList")
        }

        return sqlBuilder.toString()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class NumberFilter(
    val equals: Long? = null,
    val gt: Long? = null,
    val gte: Long? = null,
    val lt: Long? = null,
    val lte: Long? = null,
    val isIn: List<Long>? = null,
    val between : NumberRange? = null
) {
    @JsonIgnore
    fun getSqlWhereClause(field: String): String? {
        val sqlBuilder = StringBuilder()

        equals?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field = $it")
        }

        gt?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
        }

        gte?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field >= $it")
        }

        lt?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field < $it")
        }

        lte?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field <= $it")
        }

        isIn?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            val list = it.joinToString(prefix = "(", postfix = ")") //convert list to surround by parenthesis instead
            sqlBuilder.append("$field IN $list")
        }

        between?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field BETWEEN ${it.start} AND ${it.end}")
        }

        return sqlBuilder.toString()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class NumberRange (
    @JsonProperty("start")
    val start: Long,
    @JsonProperty("end")
    val end: Long
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DateFilter(
    val equals: LocalDate? = null,
    val lt: LocalDate? = null,
    val lte: LocalDate? = null,
    val gt: LocalDate? = null,
    val gte: LocalDate? = null,
    val between: DateRange? = null,
) {
    @JsonIgnore
    fun getSqlWhereClause(field: String): String? {
        val sqlBuilder = StringBuilder()

        equals?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field = '$it'")
        }

        lt?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field <'$it'")
        }

        lte?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field <= '$it'")
        }

        gt?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field >'$it'")
        }

        gte?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field >= '$it'")
        }

        between?.let {
            if (sqlBuilder.isNotEmpty()) { sqlBuilder.append(" AND ")}
            sqlBuilder.append("$field BETWEEN '${it.start}' AND '${it.end}'")
        }

        return sqlBuilder.toString()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DateRange(
    @JsonProperty("start")
    val start: LocalDate,
    @JsonProperty("end")
    val end: LocalDate
)
