package com.kottlingettingstart.main.documents


import com.kottlingettingstart.main.enums.ProfileEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Employee (
        val name: String,
        val email: String,
        val password: String,
        val cpf: String,
        val profile: ProfileEnum,
        val companyId: String,
        val hourValue: Double? = 0.0,
        val quantityHoursWorkedPerDay: Float? = 0.0f,
        val quantityHoursOfLaunch: Float? = 0.0f,
        @Id val id: String? = null
)
