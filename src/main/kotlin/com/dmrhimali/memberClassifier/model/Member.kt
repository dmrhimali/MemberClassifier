package com.dmrhimali.memberClassifier.model

import com.dmrhimali.memberClassifier.model.metadata.Gender
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.Period

@Entity
@Table(name = "member")
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "name", nullable = false, length = 200)
    val name: @Size(max = 200) @NotNull String,

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    val gender: Gender,

    //notice that in oneToOne and oneToMany relations(with mappedBy= "member" field).
    //member is an object in Address, HeathStats and Contact
    //so I have made them nullable to allow saving member without them
    //so we can then get member and pass to address ans healthstatus to be saved
    @OneToOne(mappedBy = "member", cascade = [CascadeType.ALL])
    var address: Address? = null,

    @OneToOne(mappedBy = "member", cascade = [CascadeType.ALL])
    var healthStatus: HealthStatus? = null,

    @Column(name = "date_of_birth", nullable = false)
    var dateOfBirth: @NotNull LocalDate,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], targetEntity = Contact::class)
    var contacts: Set<Contact> = mutableSetOf(),

    @Transient                       //not persisted to database
    var age: Int? = null,           //var, because member service will cal getAge and set this for member object

    ) {

    @PostLoad
    fun calculateAgeOnLoad() {
        // Automatically calculate the age when the entity is loaded from database
        this.age = getAge()
    }

    fun getAge(): Int { //in MemberRepository gtMember() method, call this and update member object before returning
        return Period.between(dateOfBirth, LocalDate.now()).years
    }
}