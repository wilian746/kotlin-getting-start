package com.kottlingettingstart.main.dtos

data class CompanyDTO (
        val companyName: String,
        val cnpj: String,
        val id: String? = null
)
