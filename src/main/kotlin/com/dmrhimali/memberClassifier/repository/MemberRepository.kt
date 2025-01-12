package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long> {

//    @Query(nativeQuery = true)
//    fun findMembersByCustomQuery(@Param("query") query: String): List<Member>
}