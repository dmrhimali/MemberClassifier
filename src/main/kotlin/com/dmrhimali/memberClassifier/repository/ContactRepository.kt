package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Long> {
    fun findByMemberId(memberId: Long) : List<Contact>
}