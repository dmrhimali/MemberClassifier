package com.dmrhimali.memberClassifier.service

import com.dmrhimali.memberClassifier.dto.request.MemberRequest
import com.dmrhimali.memberClassifier.dto.response.HealthStatusResponse
import com.dmrhimali.memberClassifier.dto.response.MemberResponse
import com.dmrhimali.memberClassifier.exceptions.*
import com.dmrhimali.memberClassifier.model.Address
import com.dmrhimali.memberClassifier.model.Contact
import com.dmrhimali.memberClassifier.model.HealthStatus
import com.dmrhimali.memberClassifier.model.Member
import com.dmrhimali.memberClassifier.repository.*
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val genderRepository: GenderRepository,
    private val healthStatusRepository: HealthStatusRepository,
    private val addressRepository: AddressRepository,
    private val contactRepository: ContactRepository,
    private val generalHealthRatingRepository: GeneralHealthRatingRepository,
    private val mentalHealthConditionRepository: MentalHealthConditionRepository,
    private val mentalHealthStatusRepository: MentalHealthStatusRepository,
    private val cholesterolLevelRepository: CholesterolLevelRepository,
    private val stressLevelRepository: StressLevelRepository,
    private val chronicConditionRepository: ChronicConditionRepository

    ) {

    fun getAllMembers(): List<MemberResponse> {
        val members = memberRepository.findAll()
        return members.map {
            MemberResponse(
                memberId = it.id,
                name = it.name,
                gender = it.gender,
                age = it.getAge(),  //default to zero if null,
                dateOfBirth = it.dateOfBirth,
                healthStatus = it.healthStatus?.let { HealthStatusResponse.fromHealthStatus(it) }
            )
        }
    }

    fun getMember(memberId: Long): MemberResponse {
        val member = memberRepository.findById(memberId)
            .orElseThrow { MemberNotFoundException("Member with ID $memberId not found.") }


        return MemberResponse(
            memberId = member.id,
            name = member.name,
            gender = member.gender,
            age = member.getAge(),  // Manually calculate age and set it to the age field. default to zero if null,
            dateOfBirth = member.dateOfBirth,
            healthStatus = member.healthStatus?.let { HealthStatusResponse.fromHealthStatus(it) }
        )
    }

    fun saveMember(memberRequest: MemberRequest): MemberResponse {
        //1. save member
        val gender = genderRepository.findByType(memberRequest.gender)
            .orElseThrow { throw GenderNotFoundException("Gender ${memberRequest.gender} not found") }

        val member = memberRepository.save(
            Member(
                name = memberRequest.name,
                gender = gender,
                dateOfBirth = memberRequest.dateOfBirth,
            )
        )
        var savedMember = memberRepository.save(member)


        //2. save address (with savedMember reference)
        val address = addressRepository.save(
            Address(
                street = memberRequest.address.street,
                city = memberRequest.address.city,
                state = memberRequest.address.state,
                zipCode = memberRequest.address.zipCode,
                member = savedMember
            )
        )

        //3. save contacts with savedMember reference
        val contacts = contactRepository.saveAll(
            memberRequest.contacts.map {
                Contact(
                    type = it.type,
                    value = it.value,
                    member = savedMember
                )
            }.toSet()
        )

        //4. save healthStatus with savedMember reference
        val generalHealthRating =
            generalHealthRatingRepository.findByType(memberRequest.healthStatus.generalHealthRating)
                .orElseThrow { throw GeneralHealthRatingNotFoundException("GeneralHealthRating ${memberRequest.healthStatus.generalHealthRating} not found") }

        val mentalHealthStatus = mentalHealthStatusRepository.findByType(memberRequest.healthStatus.mentalHealthStatus)
            .orElseThrow { throw MentalHealthStatusNotFoundException("MentalHealthStatus ${memberRequest.healthStatus.mentalHealthStatus} not found") }

        val mentalHealthHistory = memberRequest.healthStatus.mentalHealthHistory.map {
            mentalHealthConditionRepository.findByCondition(it)
                .orElseThrow { throw MentalHealthConditionNotFoundException("MentalHealthHistory $it not found") }
        }.toSet()

        val cholesterolLevel = cholesterolLevelRepository.findByLevel(memberRequest.healthStatus.cholesterolLevel)
            .orElseThrow { throw CholesterolLevelNotFoundException("CholesterolLevel ${memberRequest.healthStatus.cholesterolLevel} not found") }

        val stressLevel = stressLevelRepository.findByLevel(memberRequest.healthStatus.stressLevel)
            .orElseThrow { throw StressLevelNotFoundException("StressLevel ${memberRequest.healthStatus.stressLevel} not found") }

        val chronicConditions = memberRequest.healthStatus.chronicConditions.map {
            chronicConditionRepository.findByCondition(it)
                .orElseThrow { throw ChronicConditionNotFoundException("ChronicCondition $it not found") }
        }.toSet()

        val healthStatus = healthStatusRepository.save(
            HealthStatus(
                generalHealthRating = generalHealthRating,
                mentalHealthStatus = mentalHealthStatus,
                mentalHealthConditions = mentalHealthHistory,
                height = memberRequest.healthStatus.height,
                weight = memberRequest.healthStatus.weight,
                bmi = memberRequest.healthStatus.calculateBMI(),
                bloodPressure = memberRequest.healthStatus.bloodPressure,
                heartRate = memberRequest.healthStatus.heartRate,
                cholesterolLevel = cholesterolLevel,
                stressLevels = stressLevel,
                chronicConditions = chronicConditions,
                member = savedMember
            )
        )

        //5. update member object with address , healthStatus and contacts
        savedMember.age = savedMember.getAge()
        savedMember.address = address
        savedMember.contacts = contacts.toSet()
        savedMember.healthStatus = healthStatus


        return MemberResponse(
            memberId = savedMember.id,
            name = savedMember.name,
            gender = savedMember.gender,
            age = savedMember.age ?: 0,  //default to zero if null,
            dateOfBirth = savedMember.dateOfBirth,
            healthStatus = HealthStatusResponse.fromHealthStatus(savedMember.healthStatus)
        )
    }

}