package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Company

interface ICompanyService {
    fun findByCNPJ(cnpj: String): Company?
    fun save(company: Company): Company
}