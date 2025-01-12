package com.dmrhimali.memberClassifier.repository

import com.dmrhimali.memberClassifier.model.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository: JpaRepository<Address, Long>