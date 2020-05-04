package com.kottlingettingstart.main.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class RegistryPFDTO (
        @get:NotEmpty(message = "Name is required")
        @get:Length(min = 3, max = 200, message = "Name is required length min 3 and max 200 characters")
        val name: String = "",

        @get:NotEmpty(message = "Email is required")
        @get:Length(min = 5, max = 200, message = "Email is required length min 5 and max 200 characters")
        @get:Email(message = "Email is invalid")
        val email: String = "",

        @get:NotEmpty(message = "Password is required")
        val password: String? = null,

        @get:NotEmpty(message = "CPF is required")
        @get:CPF(message = "CPF is invalid")
        val cpf: String? = null,

        @get:NotEmpty(message = "CNPJ is required")
        @get:CNPJ(message = "CNPJ is invalid")
        val cnpj: String? = null,

        @get:NotEmpty(message = "CompanyId is required")
        val companyId: String? = null,

        val hourValue: String? = null,
        val quantityHoursWorkedPerDay: String? = null,
        val quantityHoursOfLaunch: String? = null,
        val id: String? = null
)
