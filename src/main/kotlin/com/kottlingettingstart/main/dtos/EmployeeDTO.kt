package com.kottlingettingstart.main.dtos

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class EmployeeDTO (
        @get:NotEmpty(message = "Name is required")
        @get:Length(min = 3, max = 200, message = "Name is required length min 3 and max 200 characters")
        val name: String = "",

        @get:NotEmpty(message = "Email is required")
        @get:Length(min = 5, max = 200, message = "Email is required length min 5 and max 200 characters")
        @get:Email(message = "Email is invalid")
        val email: String = "",

        val password: String? = null,
        val hourValue: String? = null,
        val quantityHoursWorkedPerDay: String? = null,
        val quantityHoursOfLaunch: String? = null,
        val id: String? = null
)