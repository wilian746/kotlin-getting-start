package com.kottlingettingstart.main.dtos

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class ReleaseDTO (
        @get:NotEmpty(message = "Date is required")
        val date: String = "",

        @get:NotEmpty(message = "Type is required")
        val type: String = "",

        val description: String? = null,
        val location: String? = null,
        val employeeId: String? = null,
        val id: String? = null
)