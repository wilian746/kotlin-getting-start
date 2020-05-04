package com.kottlingettingstart.main.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class RegistryPJDTO (
        @get:NotEmpty(message = "Name is required")
        @get:Length(min = 3, max = 200, message = "Name is required length min 3 and max 200 characters")
        val name: String = "",

        @get:NotEmpty(message = "Email is required")
        @get:Length(min = 5, max = 200, message = "Email is required length min 5 and max 200 characters")
        @get:Email(message = "Email is invalid")
        val email: String = "",

        @get:NotEmpty(message = "Password is required")
        val password: String = "",

        @get:NotEmpty(message = "CPF is required")
        @get:CPF(message = "CPF is invalid")
        val cpf: String = "",

        @get:NotEmpty(message = "CNPJ is required")
        @get:CNPJ(message = "CNPJ is invalid")
        val cnpj: String = "",

        @get:NotEmpty(message = "CompanyName is required")
        @get:Length(min = 5, max = 200, message = "CompanyName is required length min 5 and max 200 characters")
        val companyName: String = "",

        val id: String? = null
)
